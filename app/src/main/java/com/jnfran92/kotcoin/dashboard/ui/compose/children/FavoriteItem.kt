package com.jnfran92.kotcoin.dashboard.ui.compose.children

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavorite
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard
import com.jnfran92.kotcoin.dashboard.ui.compose.animation.getAlphaBrush

@Composable
fun FavoriteItem(
    item: UICryptoFavorite? = null,
    modifier: Modifier,
    alphaValue: Float = 0.5f
) {
    Card(
        modifier = modifier
    ) {
        if (item != null) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${"%.${1}f".format(item.price.price / 1000)}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    TrendingImage(item.trending)
                }
                Text(
                    text = item.symbol,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = item.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                    brush = getAlphaBrush(alphaValue = alphaValue)
                )
            )
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun FavoriteItemPreview() {
    KotcoinAppTheme {
        FavoriteItem(
            item = UIDashboard.DUMMY.listOfFavorites[0], modifier = Modifier
                .padding(8.dp)
                .padding(bottom = 16.dp)
                .width(170.dp)
                .height(150.dp)
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun DefaultFavoriteItemPreview() {
    KotcoinAppTheme {
        FavoriteItem(
            modifier = Modifier
                .padding(8.dp)
                .padding(bottom = 16.dp)
                .width(170.dp)
                .height(150.dp)
        )
    }
}
