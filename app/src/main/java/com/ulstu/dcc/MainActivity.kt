package com.ulstu.dcc

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.ulstu.resource.ui.theme.AppSize
import com.ulstu.resource.ui.theme.AppStyle
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentStyle = remember { mutableStateOf(AppStyle.Purple) }
            val currentFontSize = remember { mutableStateOf(AppSize.Medium) }

            DriverlessCarControlTheme(
                style = currentStyle.value,
                textSize = currentFontSize.value,
            ) {
                //MainScreen()
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MainActivityPreview() {
    DriverlessCarControlTheme {
        //MainScreen()
    }
}