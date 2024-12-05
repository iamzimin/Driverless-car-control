package com.ulstu.api.domain.models

sealed class PingStatusResponse {
    data class PingSuccess(val ip: String): PingStatusResponse()
    data class PingFail(val ip: String): PingStatusResponse()
}