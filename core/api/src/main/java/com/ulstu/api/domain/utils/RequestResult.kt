package com.ulstu.api.domain.utils

typealias RootError = Error

sealed interface RequestResult<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D): RequestResult<D, E>
    data class Error<out D, out E: RootError>(val url: String, val error: E): RequestResult<D, E>
}