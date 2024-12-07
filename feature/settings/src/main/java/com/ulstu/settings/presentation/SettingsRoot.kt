package com.ulstu.settings.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ulstu.settings.presentation.mvi.SettingsViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SettingsRoot(
    viewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>(),
) {
    SettingsScreen(
        state = viewModel.collectAsState().value,
        savePingTime = viewModel::savePingTime,
        saveNumberPingStreams = viewModel::saveNumberPingStreams,
    )
}