package com.ulstu.block_scanner.domain.repository

import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.models.PingStatusResponse
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.block_scanner.domain.model.BlockInfo
import kotlinx.coroutines.flow.Flow

interface BlockScannerRepository {
    suspend fun scanNetwork(): Flow<PingStatusResponse>
    suspend fun getBlockInfo(foundedIpv4: List<String>): Flow<RequestResult<BlockInfo, NetworkError>>
}