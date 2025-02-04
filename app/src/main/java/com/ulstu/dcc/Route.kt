package com.ulstu.dcc

import kotlinx.serialization.Serializable

interface Route {
    @Serializable data object BlockScanner: Route
    @Serializable data object CarControl: Route
    @Serializable data object Settings: Route

    @Serializable data class WebView(val url: String): Route
}