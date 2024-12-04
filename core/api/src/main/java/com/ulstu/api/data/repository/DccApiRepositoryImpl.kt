package com.ulstu.api.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkAddress
import android.net.NetworkCapabilities
import com.google.gson.JsonParseException
import com.ulstu.api.domain.models.SystemInfoResponse
import com.ulstu.api.domain.repository.DccApiRepository
import com.ulstu.api.domain.service.DccApi
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.Inet4Address
import java.net.InetAddress
import java.net.SocketTimeoutException

class DccApiRepositoryImpl(
    private val context: Context,
    private val dccRetrofitBuilder: Retrofit.Builder,
): DccApiRepository {
    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): RequestResult<T, NetworkError> {
        return try {
            RequestResult.Success(apiCall())
        } catch (e: JsonParseException) {
            RequestResult.Error(NetworkError.SERIALIZATION)
        } catch (e: HttpException) {
            when (e.code()) {
                408 -> RequestResult.Error(NetworkError.REQUEST_TIMEOUT)
                429 -> RequestResult.Error(NetworkError.TOO_MANY_REQUESTS)
                in 500..599 -> RequestResult.Error(NetworkError.SERVER_ERROR)
                else -> RequestResult.Error(NetworkError.UNKNOWN)
            }
        } catch (e: SocketTimeoutException) {
            RequestResult.Error(NetworkError.REQUEST_TIMEOUT)
        } catch (e: Exception) {
            RequestResult.Error(NetworkError.UNKNOWN)
        }
    }

    override suspend fun scanNetwork(): Flow<List<String>> = flow {
        val localIp = getLocalIp() ?: return@flow
        val ipRange = generateIpRange(localIp)
        val availableIps = mutableListOf<String>()

        ipRange.asFlow()
            .filter { pingIp(it) }
            .collect { ip ->
                availableIps.add(ip)
                emit(availableIps.toList())
            }
    }.flowOn(Dispatchers.IO)

    override suspend fun getSystemInfo(foundedIpv4: List<String>): RequestResult<Flow<List<SystemInfoResponse?>>, NetworkError> {
        return try {
            val flow = flow {
                val discoveredInfo = mutableListOf<SystemInfoResponse?>()

                foundedIpv4.asFlow()
                    .flatMapMerge { ip ->
                        flow {
                            val api = createApiForIp(ip)
                            val result = safeApiCall { api.getSystemInfo() }
                            if (result is RequestResult.Success) {
                                emit(result.data)
                            }
                        }
                    }
                    .collect { response ->
                        discoveredInfo.add(response)
                        emit(discoveredInfo.toList())
                    }
            }.flowOn(Dispatchers.IO)

            RequestResult.Success(flow)
        } catch (e: Exception) {
            RequestResult.Error(NetworkError.UNKNOWN)
        }
    }

    private fun createApiForIp(ip: String): DccApi {
        val baseUrl = "http://$ip/"
        return dccRetrofitBuilder.baseUrl(baseUrl).build().create(DccApi::class.java)
    }


    private fun getLocalIp(): String? {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return null
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return null

        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            val linkProperties = connectivityManager.getLinkProperties(network) ?: return null
            for (linkAddress: LinkAddress in linkProperties.linkAddresses) {
                val address = linkAddress.address
                if (address is Inet4Address) {
                    return address.hostAddress
                }
            }
        }
        return null
    }

    private fun generateIpRange(baseIp: String): List<String> {
        val prefix = baseIp.substringBeforeLast(".")
        return (0..255).map { "$prefix.$it" }
    }

    private suspend fun pingIp(ip: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                InetAddress.getByName(ip).isReachable(100)
            } catch (e: Exception) {
                false
            }
        }
    }


}