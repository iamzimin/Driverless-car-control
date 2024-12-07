package com.ulstu.resource.ui.extensions

import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.ulstu.resource.ui.theme.AppTheme

fun Modifier.clickableRipple(
    color: Color? = null,
    shape: Shape = RectangleShape,
    onClick: () -> Unit): Modifier
= composed {
    this
        .clip(shape)
        .clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple(color = color ?: AppTheme.colors.text, bounded = false),
    ) {
        onClick()
    }
}