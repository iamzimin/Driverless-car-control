package com.ulstu.block_scanner.presentation.mvi

import com.ulstu.block_scanner.presentation.model.OutputInfo

data class BlockScannerState(
    val isBlockScannerLoading: Boolean = false,
    val outputInfo: OutputInfo = OutputInfo(),
)