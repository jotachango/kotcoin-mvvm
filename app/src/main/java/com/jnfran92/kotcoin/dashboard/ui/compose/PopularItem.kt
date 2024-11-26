package com.jnfran92.kotcoin.dashboard.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard

@Composable
fun PopularItem(item: UICryptoPopular) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .padding(bottom = 8.dp)
            .height(150.dp)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(90.dp)
            ) {
                Row {
                    Text(
                        text = "$${"%.${1}f".format(item.price.price / 1000)}K",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(8.dp)
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
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun PopularItemPreview() {
    KotcoinAppTheme {
        PopularItem(item = UIDashboard.DUMMY.listOfPopular[0])
    }
}
