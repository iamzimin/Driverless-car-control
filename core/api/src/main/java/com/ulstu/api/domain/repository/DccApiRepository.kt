package com.ulstu.api.domain.repository

import com.ulstu.api.domain.models.SystemInfoResponse
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.RequestResult
import kotlinx.coroutines.flow.Flow

interface DccApiRepository {
    suspend fun scanNetwork(): Flow<List<String>>
    suspend fun getSystemInfo(foundedIpv4: List<String>): RequestResult<Flow<List<SystemInfoResponse?>>, NetworkError>
}