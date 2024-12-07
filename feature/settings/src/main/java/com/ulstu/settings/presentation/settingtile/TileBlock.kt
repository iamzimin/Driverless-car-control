package com.ulstu.settings.presentation.settingtile

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.BorderRadius
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import com.ulstu.settings.presentation.model.TextTileValue

@Composable
fun TileBlock(
    blockName: String,
    tileContents: List<@Composable () -> Unit>,
) {
    Text(
        text = blockName,
        style = AppTheme.typography.body,
        color = AppTheme.colors.tileTextInvisible,
    )

    Spacer(modifier = Modifier.height(10.dp))

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BorderRadius))
            .background(AppTheme.colors.tileBackground)
            .fillMaxWidth()
    ) {
        Column {
            tileContents.forEachIndexed { index, tileContent ->
                tileContent()
                if (index != tileContents.lastIndex) {
                    HorizontalDivider(
                        color = AppTheme.colors.secondary,
                    )
                }
            }

        }
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TileBlockPreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            TileBlock(
                blockName = "Block scanner",
                tileContents = listOf(
                    {
                        CheckedTile(
                            title = "Title example",
                            initialCheck = false,
                            onClick = {},
                        )
                    },
                    {
                        ValueTile(
                            title = "Title example",
                            initialValue = TextTileValue.StringValue("test test"),
                            onTextConfirm = {},
                        )
                    }
                )
            )
        }
    }
}