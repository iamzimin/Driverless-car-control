package com.ulstu.settings.presentation.settingtile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulstu.resource.ui.extensions.clickableRipple
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme

@Composable
fun CheckedTile(
    title: String,
    initialCheck: Boolean,
    onClick: (Boolean) -> Unit
) {
    var isChecked by remember { mutableStateOf(initialCheck) }
    
    Row(
        modifier = Modifier
            .clickableRipple {
                isChecked = !isChecked
                onClick(isChecked)
            }
            .padding(15.dp)
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = title,
            style = AppTheme.typography.body,
            color = AppTheme.colors.text,
        )

        Spacer(modifier = Modifier.width(10.dp))

        Switch(
            checked = isChecked,
            onCheckedChange = {
                isChecked = !isChecked
                onClick(it)
            },
            colors = SwitchDefaults.colors().copy(
                checkedBorderColor = AppTheme.colors.primary,
                checkedThumbColor = AppTheme.colors.primary,
                checkedTrackColor = AppTheme.colors.tileBackground,

                uncheckedBorderColor = AppTheme.colors.primary,
                uncheckedThumbColor = AppTheme.colors.primary,
                uncheckedTrackColor = AppTheme.colors.tileBackground,
            )
        )
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CheckedTilePreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            CheckedTile(
                title = "Title example",
                initialCheck = false,
                onClick = {},
            )
        }
    }
}