package com.ulstu.api.domain.utils

enum class NetworkError: Error {
    SERIALIZATION,
    NOT_FOUND,
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    CONNECT_EXCEPTION,
    PROTOCOL_EXCEPTION,
    UNKNOWN,
}