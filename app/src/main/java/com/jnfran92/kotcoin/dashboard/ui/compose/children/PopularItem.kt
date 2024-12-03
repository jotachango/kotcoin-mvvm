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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoPopular
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS2
import com.jnfran92.kotcoin.dashboard.ui.compose.animation.getAlphaBrush

@Composable
fun PopularItem(
    item: UICryptoPopular? = null,
    modifier: Modifier,
    alphaValue: Float = 0.5f
) {
    Card(
        modifier = modifier
    ) {
        if (item != null) {
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(90.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$${"%.${1}f".format(item.usdPrice / 1000)}K",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(2.dp)
                        )
                        TrendingImage(item.trending)
                    }
                    Text(
                        text = item.symbol,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                }
                VerticalDivider(Modifier.padding(8.dp))
                PopularLineChart(item.historicalUIPrice)
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
fun PopularItemPreview() {
    KotcoinAppTheme {
        PopularItem(
            item = UIDashboardS2.DUMMY.listOfPopular[0], modifier = Modifier
                .padding(8.dp)
                .padding(bottom = 8.dp)
                .height(150.dp)
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun DefaultPopularItemPreview() {
    KotcoinAppTheme {
        PopularItem(
            modifier = Modifier
                .padding(8.dp)
                .padding(bottom = 8.dp)
                .height(150.dp)
        )
    }
}