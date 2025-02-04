package com.ulstu.car_control.presentation

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ulstu.resource.R
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.roundToInt

@Composable
fun SteeringWheel(
    modifier: Modifier = Modifier,
    onAngleChange: (Float) -> Unit
) {
    var wheelAngle by rememberSaveable { mutableFloatStateOf(0f) }
    var touchAngle by rememberSaveable { mutableFloatStateOf(0f) }
    var currentSize by remember { mutableStateOf(IntSize.Zero) }
    val animatedAngle by animateFloatAsState(
        targetValue = wheelAngle,
        label = "wheelRotation"
    )
    val angleLimit = 540f

    Box(
        modifier = modifier
            .onSizeChanged { currentSize = it }
            .pointerInput(currentSize) {

                val width = currentSize.width
                val height = currentSize.height
                val center = Offset(width / 2f, height / 2f)

                detectDragGestures(
                    onDragStart = { offset ->
                        val touchVector = offset - center
                        touchAngle = Math.toDegrees(
                            atan2(touchVector.y.toDouble(), touchVector.x.toDouble())
                        ).toFloat()
                    },
                    onDrag = { change, _ ->
                        val touchVector = change.position - center
                        val newTouchAngle = Math.toDegrees(
                            atan2(touchVector.y.toDouble(), touchVector.x.toDouble())
                        ).toFloat()

                        val angleDiff = calculateAngleDifference(touchAngle, newTouchAngle)
                        var newAngle = wheelAngle + angleDiff

                        newAngle = newAngle.coerceIn(-angleLimit, angleLimit)

                        if (wheelAngle != newAngle) {
                            wheelAngle = (newAngle * 10).roundToInt() / 10f
                            onAngleChange(newAngle)
                        }

                        touchAngle = newTouchAngle
                    }
                )
            }
    ) {
        Icon(
            modifier = Modifier
                .rotate(animatedAngle),
            painter = painterResource(id = R.drawable.steering_wheel),
            contentDescription = null,
            tint = Color.Unspecified,
        )
        Text(
            modifier = Modifier.align(Alignment.TopEnd),
            text = wheelAngle.toString(),
            color = AppTheme.colors.text,
        )
        /*Canvas(
            modifier = Modifier
                .fillMaxSize()
                .rotate(animatedAngle)
        ) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.minDimension / 2

            drawCircle(
                color = Color.Gray,
                center = center,
                radius = radius
            )

            val pointerLength = radius * 0.8f
            drawLine(
                color = Color.Red,
                start = center,
                end = center + Offset(0f, -pointerLength),
                strokeWidth = 4f
            )
        }*/
    }
}

private fun calculateAngleDifference(startAngle: Float, endAngle: Float): Float {
    val difference = endAngle - startAngle
    val sign = if (difference > 0) 1 else -1
    val absoluteDifference = abs(difference)

    return when {
        absoluteDifference > 180 -> -(360 - absoluteDifference) * sign
        else -> difference
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SteeringWheelPreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            SteeringWheel(
                modifier = Modifier.size(300.dp),
                onAngleChange = {},
            )
        }
    }
}