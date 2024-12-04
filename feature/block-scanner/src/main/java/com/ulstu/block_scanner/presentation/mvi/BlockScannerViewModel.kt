package com.ulstu.block_scanner.presentation.mvi

import androidx.lifecycle.ViewModel
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.PingStatus
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.block_scanner.domain.model.SystemInfo
import com.ulstu.block_scanner.domain.usecase.BlockScannerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class BlockScannerViewModel @Inject constructor(
    private val blockScannerUseCases: BlockScannerUseCases,
): ContainerHost<BlockScannerState, BlockScannerSideEffect>, ViewModel() {
    override val container = container<BlockScannerState, BlockScannerSideEffect>(BlockScannerState())

    fun startScan() = intent {
        reduce { state.copy(isBlockScannerLoading = true) }

        val foundedIps = mutableListOf<PingStatus>()
        /*foundedIps.add(PingStatus.PingSuccess("192.168.1.1"))
        foundedIps.add(PingStatus.PingSuccess("192.168.1.111"))
        foundedIps.add(PingStatus.PingSuccess("192.168.1.202"))
        foundedIps.add(PingStatus.PingSuccess("192.168.1.178"))
        foundedIps.add(PingStatus.PingSuccess("192.168.1.198"))*/
        blockScannerUseCases.scanNetworkUseCase.invoke().collect { ips ->
            foundedIps.add(ips)
            reduce { state.copy(foundedIps = foundedIps.toList()) }
        }

        val blocks = mutableListOf<SystemInfo?>()
        val successIps = foundedIps.filterIsInstance<PingStatus.PingSuccess>().map { it.ip }

        blockScannerUseCases.getSystemInfoUseCase.invoke(successIps)
            .collect { result ->
                when (result) {
                    is RequestResult.Success -> {
                        blocks.add(result.data)
                        reduce { state.copy(foundedBlocks = blocks.toList()) }
                    }
                    is RequestResult.Error -> {
                        val error = result.error
                    }
                }
            }

        reduce { state.copy(isBlockScannerLoading = false) }
    }
}