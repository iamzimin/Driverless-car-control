package com.ulstu.block_scanner.domain.usecase

import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.block_scanner.domain.model.SystemInfo
import com.ulstu.block_scanner.domain.repository.BlockScannerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetSystemInfoUseCase @Inject constructor(
    private val blockScannerRepository: BlockScannerRepository,
) {
    suspend fun invoke(foundedIpv4: List<String>): Flow<RequestResult<SystemInfo?, NetworkError>> {
        return blockScannerRepository.getSystemInfo(foundedIpv4 = foundedIpv4)
    }
}