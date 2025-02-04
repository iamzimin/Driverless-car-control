package com.ulstu.dcc

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ulstu.resource.R

data class NavigationItem(
    @StringRes val title: Int,
    val route: Route,
    @DrawableRes val icon: Int,
) {
    companion object {
        val items = listOf(
            NavigationItem(
                title = R.string.block_scanner,
                route = Route.BlockScanner,
                icon = R.drawable.cube,
            ),
            NavigationItem(
                title = R.string.car_control,
                route = Route.CarControl,
                icon = R.drawable.steering_wheel,
            ),
            NavigationItem(
                title = R.string.settings,
                route = Route.Settings,
                icon = R.drawable.settings,
            ),
        )
    }
}