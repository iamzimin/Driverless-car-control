package com.ulstu.block_scanner.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ulstu.block_scanner.domain.model.SystemInfo
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import androidx.compose.ui.unit.dp
import com.ulstu.resource.R
import com.ulstu.resource.ui.extensions.clickableRipple
import com.ulstu.resource.ui.theme.BorderRadius
import com.ulstu.resource.ui.theme.VerticalPadding

@Composable
fun BlockInfoTile(
    blockInfo: SystemInfo,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .clip((RoundedCornerShape(BorderRadius)))
            .background(AppTheme.colors.tileBackground)
            .fillMaxWidth()
            .clickableRipple {
                onClick(blockInfo.ip)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Text(
                text = "${stringResource(id = R.string.block)} ${blockInfo.ip}",
                style = AppTheme.typography.body,
                color = AppTheme.colors.text,
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "${stringResource(id = R.string.mask)}: ${blockInfo.ma}",
                style = AppTheme.typography.small,
                color = AppTheme.colors.tileTextInvisible,
            )
            Text(
                text = "${stringResource(id = R.string.gateway)}: ${blockInfo.gw}",
                style = AppTheme.typography.small,
                color = AppTheme.colors.tileTextInvisible,
            )
            Spacer(modifier = Modifier.height(VerticalPadding))
        }
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun BlockInfoTilePreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            BlockInfoTile(
                blockInfo = SystemInfo(
                    dhcp = "123",
                    ip = "123",
                    ma = "123",
                    gw = "123",
                ),
                onClick = {},
            )
        }
    }
}