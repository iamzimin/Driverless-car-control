package com.ulstu.api.domain.utils

sealed class PingStatus {
    data class PingSuccess(val ip: String): PingStatus()
    data class PingFail(val ip: String): PingStatus()
}