package com.ulstu.block_scanner.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ulstu.block_scanner.presentation.mvi.BlockScannerViewModel
import com.ulstu.resource.LocalNavHostController
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun BlockScannerRoot(
    viewModel: BlockScannerViewModel = hiltViewModel<BlockScannerViewModel>(),
) {
    val navigation = LocalNavHostController.current

    BlockScannerScreen(
        navigation = navigation,
        state = viewModel.collectAsState().value,
        startScan = viewModel::startScan,
    )
}