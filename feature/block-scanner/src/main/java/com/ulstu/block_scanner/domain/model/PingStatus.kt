package com.ulstu.block_scanner.domain.model

sealed class PingStatus {
    data class PingSuccess(val ip: String) : PingStatus()
    data class PingFail(val ip: String) : PingStatus()
}
