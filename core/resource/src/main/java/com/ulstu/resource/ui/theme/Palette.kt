package com.ulstu.resource.ui.theme

import androidx.compose.ui.graphics.Color

data class AppPalette(
    val primary: Color,
    val secondary: Color,
    val background: Color,

    val text: Color,

    // Tile
    val tileTextInvisible: Color,
    val tileBackground: Color,

    // NavigationDrawer
    //val navigationDrawerBackground: Color,
)

enum class AppStyle {
    Purple,
}


val baseDarkPalette = AppPalette(
    primary = Color(0xFFC6B8FF),
    secondary = Color(0xFF2A2A42),
    background = Color(0xFF161622),

    text = Color(0xFFFFFFFF),

    // Tile
    tileTextInvisible = Color(0xFF6C6D8D),
    tileBackground = Color(0xFF1C1C2D),

    // NavigationDrawer
    //navigationDrawerBackground = Color(0xFF2E2E36),
)

val baseLightPalette = AppPalette(
    primary = Color(0xFFC6B8FF),
    secondary = Color(0xFFC6B8FF),
    background = Color(0xFFFFFFFF),

    text = Color(0xFF000000),

    // Tile
    tileTextInvisible = Color(0xFF6C6D8D),
    tileBackground = Color(0xFFF1F1F1),

    // NavigationDrawer
    //navigationDrawerBackground = Color(0xFF2E2E36),
)

