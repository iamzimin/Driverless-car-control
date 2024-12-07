package com.ulstu.shared_prefs.data.repository

import android.content.Context
import com.ulstu.shared_prefs.domain.repository.SharedPrefsRepository

class SharedPrefsRepositoryImpl(
    context: Context,
): SharedPrefsRepository {
    private val sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)

    override fun savePingTime(time: Int) {
        with(sharedPreferences.edit()) {
            putInt("pingTime", time)
            apply()
        }
    }

    override fun getPingTime(): Int {
        return sharedPreferences.getInt("pingTime", 1000)
    }

    override fun saveNumberPingStreams(streams: Int) {
        with(sharedPreferences.edit()) {
            putInt("pingStreams", streams)
            apply()
        }
    }

    override fun getNumberPingStreams(): Int {
        return sharedPreferences.getInt("pingStreams", 20)
    }
}