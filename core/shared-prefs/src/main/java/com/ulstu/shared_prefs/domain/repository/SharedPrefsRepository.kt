package com.ulstu.shared_prefs.domain.repository

interface SharedPrefsRepository {
    // Block scanner
    fun savePingTime(time: Int)
    fun getPingTime(): Int
    fun saveNumberPingStreams(streams: Int)
    fun getNumberPingStreams(): Int
}