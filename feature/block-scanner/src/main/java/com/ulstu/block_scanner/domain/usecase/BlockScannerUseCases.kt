package com.ulstu.block_scanner.domain.usecase

import javax.inject.Inject

class BlockScannerUseCases @Inject constructor(
    val scanNetworkUseCase: ScanNetworkUseCase,
    val getBlockInfoUseCase: GetBlockInfoUseCase,
)