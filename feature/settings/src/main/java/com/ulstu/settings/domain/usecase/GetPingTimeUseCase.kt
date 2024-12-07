package com.ulstu.settings.domain.usecase

import com.ulstu.settings.domain.repository.SettingsRepository
import javax.inject.Inject


class GetPingTimeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    fun invoke(): Int {
        return settingsRepository.getPingTime()
    }
}
