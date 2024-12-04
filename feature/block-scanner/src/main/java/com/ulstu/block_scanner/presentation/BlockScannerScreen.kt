package com.ulstu.block_scanner.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ulstu.block_scanner.presentation.mvi.BlockScannerState
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme

@Composable
fun BlockScannerScreen(
    //navigation: NavHostController,
    state: BlockScannerState,
    startScan: () -> Unit,
) {
    val context = LocalContext.current

    Column {
        Button(onClick = { startScan() }) {
            Text(text = "Start Scan")
        }
        Text(
            text = state.foundedIps.toString(),
            color = AppTheme.colors.text,
            style = AppTheme.typography.body.copy(fontSize = 7.sp),
        )
        Text(
            text = state.foundedBlocks.toString(),
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TestsListScreenPreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            BlockScannerScreen(
                //navigation = NavHostController(LocalContext.current),
                startScan = {},
                state = BlockScannerState(
                    isBlockScannerLoading = false,
                    foundedIps = listOf(),
                    foundedBlocks = listOf(),
                ),
            )
        }
    }
}