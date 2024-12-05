package com.ulstu.api.domain.repository

import com.ulstu.api.domain.models.SystemInfoResponse
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.models.PingStatusResponse
import com.ulstu.api.domain.utils.RequestResult
import kotlinx.coroutines.flow.Flow

interface DccApiRepository {
    suspend fun scanNetwork(): Flow<PingStatusResponse>
    suspend fun getSystemInfo(foundedIpv4: List<String>): Flow<RequestResult<SystemInfoResponse?, NetworkError>>
}