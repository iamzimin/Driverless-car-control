package com.ulstu.car_control.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import com.ulstu.resource.ui.theme.HorizontalPadding
import com.ulstu.resource.ui.theme.VerticalPadding

@Composable
fun CarControlScreen(

) {
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
            Button(
                onClick = {

                }
            ) {

            }
            Button(
                onClick = {

                }
            ) {

            }

            Spacer(modifier = Modifier.weight(1f))

            SteeringWheel(
                modifier = Modifier.size(200.dp)
            ) {
                println(it)
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