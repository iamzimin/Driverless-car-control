package com.ulstu.block_scanner.presentation.mvi

import com.ulstu.api.domain.utils.NetworkError

sealed class BlockScannerSideEffect {
    data object BlockScannerSuccess : BlockScannerSideEffect()
    data class BlockScannerFail(val error: NetworkError) : BlockScannerSideEffect()
}