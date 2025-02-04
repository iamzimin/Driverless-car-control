package com.ulstu.car_control.presentation

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme

@Composable
fun Pedal(
    width: Dp,
    height: Dp,
    onPress: () -> Unit = {},
    onRelease: () -> Unit = {},
) {
    val stripeHeight = 1.dp
    val stripeSpacing = 7.dp
    val numStripes = (height.value / (stripeHeight.value + stripeSpacing.value)).toInt()

    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = tween(durationMillis = 200)
    )

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        onPress()
                        tryAwaitRelease()
                        isPressed = false
                        onRelease()
                    }
                )
            }
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Gray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(numStripes) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(stripeHeight)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.LightGray),
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PedalPreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            Pedal(
                height = 70.dp,
                width = 40.dp,
            )
        }
    }
}