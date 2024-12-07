package com.ulstu.settings.domain.usecase

import com.ulstu.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SavePingTimeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    fun invoke(time: Int) {
        settingsRepository.savePingTime(time)
    }
}
