package com.ulstu.settings.domain.repository

interface SettingsRepository {
    // Block scanner
    fun savePingTime(time: Int)
    fun getPingTime(): Int
    fun saveNumberPingStreams(streams: Int)
    fun getNumberPingStreams(): Int
}