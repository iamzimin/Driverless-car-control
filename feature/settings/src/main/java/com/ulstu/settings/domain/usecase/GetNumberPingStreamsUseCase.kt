package com.ulstu.settings.domain.usecase

import com.ulstu.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class GetNumberPingStreamsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    fun invoke(): Int {
        return settingsRepository.getNumberPingStreams()
    }
}
