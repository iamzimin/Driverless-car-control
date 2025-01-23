package com.ulstu.block_scanner.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ulstu.block_scanner.domain.model.SystemInfo
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import androidx.compose.ui.unit.dp
import com.ulstu.block_scanner.domain.model.BlockInfo
import com.ulstu.block_scanner.domain.model.UnitInfo
import com.ulstu.resource.R
import com.ulstu.resource.ui.extensions.clickableRipple
import com.ulstu.resource.ui.theme.BorderRadius

@Composable
fun BlockInfoTile(
    blockInfo: BlockInfo,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .clip((RoundedCornerShape(BorderRadius)))
            .background(AppTheme.colors.tileBackground)
            .fillMaxWidth()
            .clickableRipple {
                onClick(blockInfo.systemInfo.ip)
            }
    ) {

        Column(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Text(
                text = blockInfo.unitInfo.unitName,
                style = AppTheme.typography.body,
                color = AppTheme.colors.text,
            )

            Spacer(modifier = Modifier.height(10.dp))

            InfoRow(
                iconRes = R.drawable.globe,
                text = blockInfo.systemInfo.ip
            )

            InfoRow(
                iconRes = R.drawable.hardware,
                text = "${stringResource(R.string.hw)}: ${blockInfo.unitInfo.hardwareVer} " +
                        "${stringResource(R.string.fw)}: ${blockInfo.unitInfo.firmwareVer}"
            )

            InfoRow(
                iconRes = R.drawable.mask,
                text = blockInfo.systemInfo.ma,
            )

            InfoRow(
                iconRes = R.drawable.gate,
                text = blockInfo.systemInfo.gw,
            )

            InfoRow(
                iconRes = R.drawable.time,
                text = "${blockInfo.unitInfo.buildDate} ${blockInfo.unitInfo.buildTime}"
            )
        }

        val backgroundColor = AppTheme.colors.background
        Icon(
            modifier = Modifier
                .size(40.dp)
                .rotate(-45f)
                .drawBehind {
                    drawCircle(
                        color = backgroundColor,
                        radius = this.size.minDimension / 2 + 5.dp.toPx(),
                        center = this.center,
                    )
                }
                .align(Alignment.BottomEnd),
            painter = painterResource(R.drawable.arrow_right_circle),
            contentDescription = null,
            tint = AppTheme.colors.primary,
        )
    }
}


@Composable
private fun InfoRow(iconRes: Int, text: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(15.dp),
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = AppTheme.colors.tileTextInvisible
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = AppTheme.typography.small,
            color = AppTheme.colors.tileTextInvisible,
        )
    }
}



@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun BlockInfoTilePreview(darkTheme: Boolean = true) {
    DriverlessCarControlTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            BlockInfoTile(
                blockInfo = BlockInfo(
                    systemInfo = SystemInfo(
                        ip = "123",
                        ma = "123",
                        gw = "123",
                    ),
                    unitInfo = UnitInfo(
                        unitName = "Блок BMS и инверторов",
                        firmwareVer = "2.1",
                        hardwareVer = "2.0",
                        buildDate = "Jan 23 2025",
                        buildTime = "01:31:45",
                    )
                ),
                onClick = {},
            )
        }
    }
}