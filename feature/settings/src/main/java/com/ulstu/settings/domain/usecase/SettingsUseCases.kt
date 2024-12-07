package com.ulstu.settings.domain.usecase

import javax.inject.Inject

data class SettingsUseCases @Inject constructor(
    // Block scanner
    val savePingTimeUseCase: SavePingTimeUseCase,
    val getPingTimeUseCase: GetPingTimeUseCase,
    val saveNumberPingStreamsUseCase: SaveNumberPingStreamsUseCase,
    val getNumberPingStreamsUseCase: GetNumberPingStreamsUseCase,
)
