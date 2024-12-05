package com.ulstu.block_scanner.domain.usecase

import com.ulstu.api.domain.models.PingStatusResponse
import com.ulstu.block_scanner.domain.repository.BlockScannerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ScanNetworkUseCase @Inject constructor(
    private val blockScannerRepository: BlockScannerRepository,
) {
    suspend fun invoke(): Flow<PingStatusResponse> {
        return blockScannerRepository.scanNetwork()
    }
}