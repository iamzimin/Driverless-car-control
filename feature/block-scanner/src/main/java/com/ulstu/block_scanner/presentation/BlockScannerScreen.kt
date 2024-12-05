package com.ulstu.block_scanner.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.block_scanner.domain.model.SystemInfo
import com.ulstu.block_scanner.presentation.model.OutputInfo
import com.ulstu.resource.R
import com.ulstu.block_scanner.presentation.mvi.BlockScannerState
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.BorderRadius
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import com.ulstu.resource.ui.theme.HorizontalPadding
import com.ulstu.resource.ui.theme.VerticalPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BlockScannerScreen(
    navigation: NavHostController,
    state: BlockScannerState,
    startScan: () -> Unit,
) {
    val context = LocalContext.current
    val outputInfoScrollState = rememberScrollState()
    val refreshingState = rememberSwipeRefreshState(isRefreshing = false)
    val successSystemInfoBlocks = state.outputInfo.foundedBlocks
        ?.filterIsInstance<RequestResult.Success<SystemInfo?, NetworkError>>()
        ?.mapNotNull { it.data }
    val lazyColumnSpacedBy = 10.dp

    LaunchedEffect(state.outputInfo) {
        outputInfoScrollState.animateScrollTo(outputInfoScrollState.maxValue)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = HorizontalPadding, vertical = VerticalPadding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip((RoundedCornerShape(BorderRadius)))
                .background(AppTheme.colors.tileBackground)
        ) {
            CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                        .verticalScroll(outputInfoScrollState),
                    text = state.outputInfo.toConsole(context = context),
                    style = AppTheme.typography.small,
                    color = AppTheme.colors.text,
                )
            }
        }

        Spacer(modifier = Modifier.height(VerticalPadding))

        Text(
            text = stringResource(id = R.string.detected_blocks),
            style = AppTheme.typography.heading,
            color = AppTheme.colors.text,
        )

        Spacer(modifier = Modifier.height(VerticalPadding))

        SwipeRefresh(
            state = refreshingState,
            onRefresh = { startScan() },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    backgroundColor = AppTheme.colors.background,
                    contentColor = AppTheme.colors.primary,
                )
            },
            swipeEnabled = !state.isBlockScannerLoading,
        ) {
            if (state.isBlockScannerLoading) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(lazyColumnSpacedBy)
                ) {
                    items(6) {
                        BlockInfoTileShimmer()
                    }
                }
            } else if (successSystemInfoBlocks.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(100.dp),
                            painter = painterResource(id = R.drawable.sad),
                            contentDescription = null,
                            tint = AppTheme.colors.tileTextInvisible,
                        )

                        Spacer(modifier = Modifier.height(VerticalPadding))

                        Text(
                            text = "${stringResource(id = R.string.blocks_not_found)}.\n" +
                                    "${stringResource(id = R.string.swipe_to_update)}.",
                            style = AppTheme.typography.body,
                            color = AppTheme.colors.tileTextInvisible,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(lazyColumnSpacedBy)
                ) {
                    items(
                        count = successSystemInfoBlocks.size,
                    ) { index ->
                        BlockInfoTile(
                            blockInfo = successSystemInfoBlocks[index],
                            onClick = {
                                navigation.navigate("webview-screen/$it")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TestsListScreenPreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            BlockScannerScreen(
                navigation = NavHostController(LocalContext.current),
                startScan = {},
                state = BlockScannerState(
                    isBlockScannerLoading = false,
                    outputInfo = OutputInfo(
                        foundedBlocks = listOf(
                            RequestResult.Success(SystemInfo(
                                dhcp = "123",
                                ip = "123",
                                ma = "123",
                                gw = "123",)
                            ),
                        )
                    )
                ),
            )
        }
    }
}