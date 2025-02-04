package com.ulstu.block_scanner.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ulstu.block_scanner.presentation.mvi.BlockScannerViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun BlockScannerRoot(
    viewModel: BlockScannerViewModel = hiltViewModel<BlockScannerViewModel>(),
    onWebViewScreen: (url: String) -> Unit,
) {

    BlockScannerScreen(
        onWebViewScreen = onWebViewScreen,
        state = viewModel.collectAsState().value,
        startScan = viewModel::startScan,
    )
}