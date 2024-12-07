package com.ulstu.settings.presentation.mvi

import androidx.lifecycle.ViewModel
import com.ulstu.settings.domain.usecase.SettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsUseCases: SettingsUseCases,
): ContainerHost<SettingsState, SettingsSideEffect>, ViewModel() {
    override val container = container<SettingsState, SettingsSideEffect>(
        SettingsState(
            pingTime = settingsUseCases.getPingTimeUseCase.invoke(),
            numberPingStreams = settingsUseCases.getNumberPingStreamsUseCase.invoke(),
        )
    )

    fun savePingTime(time: Int) = intent {
        settingsUseCases.savePingTimeUseCase.invoke(time = time)
        reduce { state.copy(pingTime = time) }
    }

    fun saveNumberPingStreams(streams: Int) = intent {
        settingsUseCases.saveNumberPingStreamsUseCase.invoke(streams)
        reduce { state.copy(numberPingStreams = streams) }
    }
}