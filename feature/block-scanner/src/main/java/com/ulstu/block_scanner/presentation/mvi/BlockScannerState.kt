package com.ulstu.block_scanner.presentation.mvi

import com.ulstu.api.domain.utils.PingStatus
import com.ulstu.block_scanner.domain.model.SystemInfo

data class BlockScannerState(
    val isBlockScannerLoading: Boolean = false,
    val foundedIps: List<PingStatus> = listOf(),
    val foundedBlocks: List<SystemInfo?> = listOf(),
)