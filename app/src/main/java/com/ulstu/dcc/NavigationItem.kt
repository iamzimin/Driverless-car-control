package com.ulstu.dcc

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val route: Route,
    val icon: ImageVector,
) {
    companion object {
        val items = listOf(
            NavigationItem(
                title = "Block scanner",
                route = Route.BlockScanner,
                icon = Icons.Filled.Home,
            ),
            NavigationItem(
                title = "Settings",
                route = Route.Settings,
                icon = Icons.Filled.Settings,
            ),
        )
    }
}