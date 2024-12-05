package com.ulstu.api.domain.utils

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    SERIALIZATION,
    CONNECT_EXCEPTION,
    UNKNOWN,
}