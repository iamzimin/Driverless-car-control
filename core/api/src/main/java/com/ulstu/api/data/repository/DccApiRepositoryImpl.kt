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
import com.ulstu.api.domain.models.PingStatusResponse
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.shared_prefs.domain.repository.SharedPrefsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.ConnectException
import java.net.Inet4Address
import java.net.InetAddress
import java.net.SocketTimeoutException

class DccApiRepositoryImpl(
    private val context: Context,
    private val dccRetrofitBuilder: Retrofit.Builder,
    private val sharedPrefsRepository: SharedPrefsRepository,
): DccApiRepository {
    private suspend fun <T> safeApiCall(url: String, apiCall: suspend () -> T): RequestResult<T, NetworkError> {
        return try {
            RequestResult.Success(apiCall())
        } catch (e: JsonParseException) {
            RequestResult.Error(url, NetworkError.SERIALIZATION)
        } catch (e: HttpException) {
            when (e.code()) {
                408 -> RequestResult.Error(url, NetworkError.REQUEST_TIMEOUT)
                429 -> RequestResult.Error(url, NetworkError.TOO_MANY_REQUESTS)
                in 500..599 -> RequestResult.Error(url, NetworkError.SERVER_ERROR)
                else -> RequestResult.Error(url, NetworkError.UNKNOWN)
            }
        } catch (e: SocketTimeoutException) {
            RequestResult.Error(url, NetworkError.REQUEST_TIMEOUT)
        } catch (e: ConnectException) {
            RequestResult.Error(url, NetworkError.CONNECT_EXCEPTION)
        } catch (e: Exception) {
            RequestResult.Error(url, NetworkError.UNKNOWN)
        }
    }

    @OptIn(FlowPreview::class)
    override suspend fun scanNetwork(): Flow<PingStatusResponse> = flow {
        val localIp = getLocalIp() ?: return@flow
        val ipRange = generateIpRange(localIp)
        val concurrency = sharedPrefsRepository.getNumberPingStreams().coerceAtLeast(1)

        ipRange.asFlow()
            .flatMapMerge(concurrency = concurrency) { ip ->
                flow {
                    val status = if (pingIp(ip)) {
                        PingStatusResponse.PingSuccess(ip = ip)
                    } else {
                        PingStatusResponse.PingFail(ip = ip)
                    }
                    emit(status)
                }
            }
            .collect { status ->
                emit(status)
            }
    }.flowOn(Dispatchers.IO)


    override suspend fun getSystemInfo(foundedIpv4: List<String>): Flow<RequestResult<SystemInfoResponse?, NetworkError>> = flow {
        for (ip in foundedIpv4) {
            val retrofit = createApiForIp(ip)
            val result = safeApiCall(
                url = ip,
                apiCall = retrofit::getSystemInfo,
            )
            emit(result)
        }
    }.flowOn(Dispatchers.IO)


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
        val timeout = sharedPrefsRepository.getPingTime().coerceAtLeast(1)

        return withContext(Dispatchers.IO) {
            try {
                InetAddress.getByName(ip).isReachable(timeout)
            } catch (e: Exception) {
                false
            }
        }
    }

}