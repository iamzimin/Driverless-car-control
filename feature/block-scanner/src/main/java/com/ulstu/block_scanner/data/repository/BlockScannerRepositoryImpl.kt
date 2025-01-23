package com.ulstu.block_scanner.data.repository

import com.ulstu.api.domain.models.PingStatusResponse
import com.ulstu.api.domain.repository.DccApiRepository
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.block_scanner.domain.mapper.toBlockInfo
import com.ulstu.block_scanner.domain.model.BlockInfo
import com.ulstu.block_scanner.domain.repository.BlockScannerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BlockScannerRepositoryImpl(
    private val dccApiRepository: DccApiRepository,
): BlockScannerRepository {
    override suspend fun scanNetwork(): Flow<PingStatusResponse> {
        return dccApiRepository.scanNetwork()
    }

    override suspend fun getBlockInfo(foundedIpv4: List<String>): Flow<RequestResult<BlockInfo, NetworkError>> = flow {
        dccApiRepository.getBlockInfo(foundedIpv4).collect { result ->
            when (result) {
                is RequestResult.Success -> {
                    val mapped = result.data.toBlockInfo()
                    emit(RequestResult.Success(mapped))
                }
                is RequestResult.Error -> {
                    emit(RequestResult.Error(result.url, result.error))
                }
            }
        }
    }

}