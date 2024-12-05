package com.ulstu.dcc

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
) {
    companion object {
        val items = listOf(
            NavigationItem(
                title = "Block scanner",
                route = "block-scanner",
                icon = Icons.Filled.Home,
            ),
            NavigationItem(
                title = "Settings",
                route = "block-scanner",
                icon = Icons.Filled.Settings,
            ),
        )
    }
}