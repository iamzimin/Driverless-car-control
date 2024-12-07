package com.ulstu.settings.presentation.settingtile

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ulstu.resource.ui.extensions.clickableRipple
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import com.ulstu.resource.R
import com.ulstu.resource.ui.theme.BorderRadius
import com.ulstu.settings.presentation.mapper.toValue
import com.ulstu.settings.presentation.model.TextTileValue

@Composable
fun ValueTile(
    title: String,
    initialValue: TextTileValue,
    onTextConfirm: (TextTileValue) -> Unit
) {
    var currentValue by remember { mutableStateOf(initialValue) }
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clickableRipple {
                showDialog = true
            }
            .padding(15.dp)
            .fillMaxWidth()
            .height(45.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = title,
            style = AppTheme.typography.body,
            color = AppTheme.colors.text,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            modifier = Modifier
                .width(70.dp),
            text = initialValue.toValue(),
            style = AppTheme.typography.body,
            color = AppTheme.colors.text,
            textAlign = TextAlign.Right,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            modifier = Modifier
                .size(10.dp),
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "arrow",
            tint = AppTheme.colors.text,
        )
    }

    if (showDialog) {
        AlertDialog(
            containerColor = AppTheme.colors.background,
            shape = RoundedCornerShape(BorderRadius),
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "${stringResource(id = R.string.enter)} ${title.lowercase()}",
                    style = AppTheme.typography.body,
                    color = AppTheme.colors.text,
                )
            },
            text = {
                TextField(
                    value = currentValue.toValue(),
                    onValueChange = { newValue ->
                        currentValue = when (initialValue) {
                            is TextTileValue.UIntValue ->
                                TextTileValue.UIntValue(newValue.toIntOrNull()?.coerceIn(0, Int.MAX_VALUE) ?: 0)
                            is TextTileValue.IntValue -> {
                                TextTileValue.IntValue(newValue.toIntOrNull() ?: 0)
                            }
                            is TextTileValue.StringValue -> {
                                TextTileValue.StringValue(newValue)
                            }
                        }
                    },
                    textStyle = AppTheme.typography.body.copy(
                        color = AppTheme.colors.text,
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = when (initialValue) {
                            is TextTileValue.UIntValue, is TextTileValue.IntValue -> KeyboardType.Number
                            is TextTileValue.StringValue -> KeyboardType.Unspecified
                        }
                    ),
                    singleLine = true,
                    colors = TextFieldDefaults.colors().copy(
                        cursorColor = AppTheme.colors.primary,
                        focusedContainerColor = AppTheme.colors.tileBackground,
                        unfocusedContainerColor = AppTheme.colors.tileBackground,
                        errorContainerColor = AppTheme.colors.tileBackground,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                    ),
                    shape = (RoundedCornerShape(BorderRadius)),
                )
            },
            confirmButton = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextButton(
                        modifier = Modifier
                            .height(40.dp)
                            .clip(RoundedCornerShape(BorderRadius))
                            .fillMaxWidth()
                            .background(AppTheme.colors.secondary),
                        shape = RoundedCornerShape(BorderRadius),
                        onClick = {
                            onTextConfirm(currentValue)
                            showDialog = false
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.submit),
                            style = AppTheme.typography.body,
                            color = AppTheme.colors.text,
                        )
                    }
                }
            },
        )
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TextTilePreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            ValueTile(
                title = "Title example",
                initialValue = TextTileValue.StringValue("text"),
                onTextConfirm = {},
            )
        }
    }
}