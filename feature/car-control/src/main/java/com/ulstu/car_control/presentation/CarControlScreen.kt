package com.ulstu.car_control.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import com.ulstu.resource.ui.theme.HorizontalPadding
import com.ulstu.resource.ui.theme.VerticalPadding

@Composable
fun CarControlScreen(

) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = VerticalPadding,
                start = HorizontalPadding,
                end = HorizontalPadding,
            ),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Pedal(
                    height = 60.dp,
                    width = 100.dp,
                    onPress = {
                        Toast.makeText(context, "press brake", Toast.LENGTH_SHORT).show()
                    },
                    onRelease = {
                        Toast.makeText(context, "release brake", Toast.LENGTH_SHORT).show()
                    },
                )

                Spacer(modifier = Modifier.width(10.dp))

                Pedal(
                    height = 120.dp,
                    width = 60.dp,
                    onPress = {
                        Toast.makeText(context, "press gas", Toast.LENGTH_SHORT).show()
                    },
                    onRelease = {
                        Toast.makeText(context, "release gas", Toast.LENGTH_SHORT).show()
                    },
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            SteeringWheel(
                modifier = Modifier
                    .sizeIn(maxHeight = 300.dp, maxWidth = 300.dp)
            ) {

            }
        }


    }
}



@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CarControlScreenPreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            CarControlScreen(

            )
        }
    }
}