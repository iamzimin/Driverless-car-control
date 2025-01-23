package com.ulstu.block_scanner.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.block_scanner.domain.mapper.toPingStatus
import com.ulstu.block_scanner.domain.model.PingStatus
import com.ulstu.block_scanner.domain.model.BlockInfo
import com.ulstu.block_scanner.domain.usecase.BlockScannerUseCases
import com.ulstu.block_scanner.presentation.model.OutputInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class BlockScannerViewModel @Inject constructor(
    private val blockScannerUseCases: BlockScannerUseCases,
): ContainerHost<BlockScannerState, BlockScannerSideEffect>, ViewModel() {
    override val container = container<BlockScannerState, BlockScannerSideEffect>(BlockScannerState())
    private var scanJob: Job? = null

    fun startScan() = intent {
        scanJob?.cancel()

        scanJob = viewModelScope.launch {
            reduce { state.copy(isBlockScannerLoading = true) }
            reduce { state.copy(outputInfo = OutputInfo()) }

            val foundedIps = mutableListOf<PingStatus>()
            blockScannerUseCases.scanNetworkUseCase.invoke().collect { ips ->
                foundedIps.add(ips.toPingStatus())
                reduce { state.copy(
                    outputInfo = state.outputInfo.copy(foundedIps = foundedIps.toList())
                )}
            }

            val blocks = mutableListOf<RequestResult<BlockInfo, NetworkError>>()
            val successIps = foundedIps.filterIsInstance<PingStatus.PingSuccess>().map { it.ip }
            blockScannerUseCases.getBlockInfoUseCase.invoke(successIps).collect { result ->
                blocks.add(result)
                reduce { state.copy(
                    outputInfo = state.outputInfo.copy(foundedBlocks = blocks.toList())
                )}
            }

            reduce { state.copy(isBlockScannerLoading = false) }
        }
    }

}