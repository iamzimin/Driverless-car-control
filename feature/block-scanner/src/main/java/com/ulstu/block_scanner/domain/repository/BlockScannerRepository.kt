package com.ulstu.block_scanner.domain.repository

import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.PingStatus
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.block_scanner.domain.model.SystemInfo
import kotlinx.coroutines.flow.Flow

interface BlockScannerRepository {
    suspend fun scanNetwork(): Flow<PingStatus>
    suspend fun getSystemInfo(foundedIpv4: List<String>): Flow<RequestResult<SystemInfo?, NetworkError>>
}