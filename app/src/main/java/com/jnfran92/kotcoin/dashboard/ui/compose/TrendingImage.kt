package com.jnfran92.kotcoin.dashboard.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavoriteTrending
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard

@Composable
fun TrendingImage(itemTrending: UICryptoFavoriteTrending,) {
    Image(
        painter = painterResource(
            id =
            when (itemTrending) {
                UICryptoFavoriteTrending.NotTrending -> {
                    R.drawable.baseline_arrow_drop_up_24
                }

                UICryptoFavoriteTrending.TrendingDown -> {
                    R.drawable.baseline_arrow_drop_down_24
                }

                UICryptoFavoriteTrending.TrendingUp -> {
                    R.drawable.baseline_arrow_drop_up_24
                }
            }
        ),
        contentDescription = "trending",
        modifier = Modifier.size(35.dp),
        colorFilter = ColorFilter.tint(
            when (itemTrending) {
                UICryptoFavoriteTrending.NotTrending -> {
                    MaterialTheme.colorScheme.tertiary
                }

                UICryptoFavoriteTrending.TrendingDown -> {
                    Color.Red
                }

                UICryptoFavoriteTrending.TrendingUp -> {
                    Color.Green
                }
            }
        )
    )
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun TrendingImagePreview() {
    KotcoinAppTheme {
        TrendingImage(itemTrending = UIDashboard.DUMMY.listOfFavorites[0].trending)
    }
}
