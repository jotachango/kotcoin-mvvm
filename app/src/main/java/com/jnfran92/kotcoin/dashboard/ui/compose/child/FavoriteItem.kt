package com.jnfran92.kotcoin.dashboard.ui.compose.child

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun FavoriteItem(item: UICryptoFavorite) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .padding(bottom = 16.dp)
            .width(170.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$${"%.${1}f".format(item.price.price / 1000)}K",
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
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun FavoriteItemPreview() {
    KotcoinAppTheme {
        FavoriteItem(item = UIDashboard.DUMMY.listOfFavorites[0])
    }
}
