package com.ulstu.resource.ui.theme

import androidx.compose.ui.graphics.Color

data class AppPalette(
    val primary: Color,
    val secondary: Color,
    val background: Color,

    val text: Color,

    // TextField
    val textField: Color,
    val textFieldBackground: Color,
)

enum class AppStyle {
    Purple,
}


val baseDarkPalette = AppPalette(
    primary = Color(0xFFC6B8FF),
    secondary = Color(0xFF4F378B),
    background = Color(0xFF161622),

    text = Color(0xFFFFFFFF),

    // TextField
    textField = Color(0xFF6C6D8D),
    textFieldBackground = Color(0xFF1C1C2D),
)

val baseLightPalette = AppPalette(
    primary = Color(0xFFC6B8FF),
    secondary = Color(0xFFC6B8FF),
    background = Color(0xFFFFFFFF),

    text = Color(0xFF000000),

    // TextField
    textField = Color(0xFF6C6D8D),
    textFieldBackground = Color(0xFFF1F1F1),
)

