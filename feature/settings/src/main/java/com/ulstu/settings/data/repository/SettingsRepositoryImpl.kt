package com.ulstu.settings.data.repository

import com.ulstu.settings.domain.repository.SettingsRepository
import com.ulstu.shared_prefs.domain.repository.SharedPrefsRepository

class SettingsRepositoryImpl(
    private val sharedPrefsRepository: SharedPrefsRepository,
): SettingsRepository {
    override fun savePingTime(time: Int) {
        sharedPrefsRepository.savePingTime(time = time)
    }

    override fun getPingTime(): Int {
        return sharedPrefsRepository.getPingTime()
    }

    override fun saveNumberPingStreams(streams: Int) {
        sharedPrefsRepository.saveNumberPingStreams(streams = streams)
    }

    override fun getNumberPingStreams(): Int {
        return sharedPrefsRepository.getNumberPingStreams()
    }

}