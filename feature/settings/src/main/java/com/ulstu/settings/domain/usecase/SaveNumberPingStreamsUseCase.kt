package com.ulstu.settings.domain.usecase

import com.ulstu.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SaveNumberPingStreamsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    fun invoke(streams: Int) {
        settingsRepository.saveNumberPingStreams(streams)
    }
}
