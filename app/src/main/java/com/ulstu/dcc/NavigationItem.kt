package com.ulstu.dcc

import androidx.annotation.DrawableRes
import com.ulstu.resource.R

data class NavigationItem(
    val title: String,
    val route: Route,
    @DrawableRes val icon: Int,
) {
    companion object {
        val items = listOf(
            NavigationItem(
                title = "Block scanner",
                route = Route.BlockScanner,
                icon = R.drawable.cube,
            ),
            NavigationItem(
                title = "Car control",
                route = Route.CarControl,
                icon = R.drawable.steering_wheel,
            ),
            NavigationItem(
                title = "Settings",
                route = Route.Settings,
                icon = R.drawable.settings,
            ),
        )
    }
}