package com.ulstu.dcc

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ulstu.block_scanner.presentation.BlockScannerRoot
import com.ulstu.resource.LocalNavHostController
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val startDestination = "block-scanner"

    CompositionLocalProvider(LocalNavHostController provides navController) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = AppTheme.colors.background,
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(route = "block-scanner") {
                    BlockScannerRoot()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MainScreenPreview() {
    DriverlessCarControlTheme {
        MainScreen()
    }
}