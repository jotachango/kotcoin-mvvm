package com.jnfran92.kotcoin.dashboard.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnfran92.kotcoin.R
import com.jnfran92.kotcoin.common.ui.theme.KotcoinAppTheme
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavorite
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavoriteTrending
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboard

@Composable
fun DashboardScreen(innerPadding: PaddingValues, uiDashboard: UIDashboard) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        item {
            DashboardSectionTitle("⭐", "Favorites")
        }

        item {
            LazyRow {
                items(uiDashboard.listOfFavorites) { item ->
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
            }
        }

        item {
            DashboardSectionTitle("📈", "Populars")
        }
        items(uiDashboard.listOfPopular) { item ->
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
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(90.dp)) {
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
                    PopularLineChartView(item.historicalUIPrice)
                }
            }
        }
    }
}

@Composable
private fun TrendingImage(itemTrending: UICryptoFavoriteTrending) {
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


@Composable
private fun DashboardSectionTitle(emoji: String, title: String) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = emoji,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun ComposablePreview() {
    KotcoinAppTheme {
        DashboardScreen(
            innerPadding = PaddingValues.Absolute(),
            uiDashboard = UIDashboard.DUMMY
        )
    }
}