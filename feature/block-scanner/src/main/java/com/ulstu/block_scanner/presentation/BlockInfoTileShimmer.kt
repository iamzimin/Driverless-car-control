package com.ulstu.block_scanner.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.ulstu.resource.ui.theme.AppTheme
import com.ulstu.resource.ui.theme.DriverlessCarControlTheme
import androidx.compose.ui.unit.dp
import com.ulstu.resource.ui.theme.BorderRadius
import com.ulstu.resource.ui.theme.VerticalPadding
import com.valentinilk.shimmer.shimmer

@Composable
fun BlockInfoTileShimmer() {
    val shimmerColor = if (isSystemInDarkTheme()) Color.LightGray else Color.Gray

    Box(
        modifier = Modifier
            .clip((RoundedCornerShape(BorderRadius)))
            .background(AppTheme.colors.tileBackground)
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Box(
                modifier = Modifier.shimmer(),
            ) {
                Box(
                    modifier = Modifier
                        .height(15.dp)
                        .width(160.dp)
                        .clip(shape = RoundedCornerShape(BorderRadius))
                        .background(shimmerColor)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            InfoShimmer(shimmerColor = shimmerColor, width = 120.dp)
            Spacer(modifier = Modifier.height(5.dp))
            InfoShimmer(shimmerColor = shimmerColor, width = 110.dp)
            Spacer(modifier = Modifier.height(5.dp))
            InfoShimmer(shimmerColor = shimmerColor, width = 120.dp)
            Spacer(modifier = Modifier.height(5.dp))
            InfoShimmer(shimmerColor = shimmerColor, width = 100.dp)
            Spacer(modifier = Modifier.height(5.dp))
            InfoShimmer(shimmerColor = shimmerColor, width = 150.dp)
        }
    }
}

@Composable
private fun InfoShimmer(shimmerColor: Color, width: Dp) {
    Box(
        modifier = Modifier.shimmer(),
    ) {
        Box(
            modifier = Modifier
                .height(10.dp)
                .width(width)
                .clip(shape = RoundedCornerShape(BorderRadius))
                .background(shimmerColor)
        )
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun BlockInfoTileShimmerPreview(darkTheme: Boolean = true) {
    Column {
        DriverlessCarControlTheme(darkTheme = darkTheme) {
            Surface(color = AppTheme.colors.background) {
                BlockInfoTileShimmer()
            }
        }
        BlockInfoTilePreview(darkTheme = darkTheme)
    }
}