package com.ulstu.settings.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import com.ulstu.resource.ui.theme.HorizontalPadding
import com.ulstu.resource.ui.theme.VerticalPadding
import com.ulstu.resource.R
import com.ulstu.settings.presentation.model.TextTileValue
import com.ulstu.settings.presentation.mvi.SettingsState
import com.ulstu.settings.presentation.settingtile.ValueTile
import com.ulstu.settings.presentation.settingtile.TileBlock

@Composable
fun SettingsScreen(
    state: SettingsState,
    savePingTime: (Int) -> Unit,
    saveNumberPingStreams: (Int) -> Unit,
) {
    val context = LocalContext.current
    val invalidDataFormat = stringResource(id = R.string.invalid_data_format)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = VerticalPadding,
                start = HorizontalPadding,
                end = HorizontalPadding,
            )
            .verticalScroll(rememberScrollState())
    ) {
        TileBlock(
            blockName = stringResource(id = R.string.block_scanner),
            tileContents = listOf(
                {
                    ValueTile(
                        title = stringResource(id = R.string.pint_time_ms),
                        initialValue = TextTileValue.UIntValue(state.pingTime),
                        onTextConfirm = { newValue ->
                            when (newValue) {
                                is TextTileValue.UIntValue -> savePingTime(newValue.value)
                                else -> Toast.makeText(context, invalidDataFormat, Toast.LENGTH_SHORT).show()
                            }
                        },
                    )
                },
                {
                    ValueTile(
                        title = stringResource(id = R.string.number_ping_streams),
                        initialValue = TextTileValue.UIntValue(state.numberPingStreams),
                        onTextConfirm = { newValue ->
                            when (newValue) {
                                is TextTileValue.UIntValue -> saveNumberPingStreams(newValue.value)
                                else -> Toast.makeText(context, invalidDataFormat, Toast.LENGTH_SHORT).show()
                            }
                        },
                    )
                },
            ),
        )
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SettingsScreenPreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            SettingsScreen(
                state = SettingsState(
                    pingTime = 1000,
                    numberPingStreams = 20,
                ),
                savePingTime = {},
                saveNumberPingStreams = {},
            )
        }
    }
}