package com.jnfran92.kotcoin.dashboard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import com.jnfran92.kotcoin.crypto.presentation.model.UICryptoDetails
import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DashboardView()
            }
        }
    }
}

sealed interface UICryptoFavoriteTrending {
    object TrendingUp : UICryptoFavoriteTrending
    object TrendingDown : UICryptoFavoriteTrending
    object NotTrending : UICryptoFavoriteTrending
}

data class UICryptoFavorite(
    val id: Int,
    val name: String,
    val symbol: String,
    val price: UIPrice,
    val trending: UICryptoFavoriteTrending
)

data class UICryptoPopular(
    val id: Int,
    val name: String,
    val symbol: String,
    val historicalUIPrice: List<UIPrice>
)

data class UIDashboard(
    val listOfFavorites: List<UICryptoFavorite> = listOf(),
    val listOfPopular: List<UICryptoPopular> = listOf()
)

val uiCryptoFavorites = listOf(
    UICryptoFavorite(
        id = 1,
        name = "Bitcoin",
        symbol = "BTC",
        price = UIPrice(
            price = 30125.50,
            marketCap = 575900000000.0,
            volume24h = 15100000000.0,
            percentChange1h = 0.05,
            percentChange24h = -0.2,
            percentChange7d = 1.3,
            lastUpdated = "2023-07-28T14:50:00.000Z"
        ),
        trending = UICryptoFavoriteTrending.TrendingUp
    ),
    UICryptoFavorite(
        id = 2,
        name = "Ethereum",
        symbol = "ETH",
        price = UIPrice(
            price = 1885.75,
            marketCap = 226700000000.0,
            volume24h = 7450000000.0,
            percentChange1h = -0.02,
            percentChange24h = -0.3,
            percentChange7d = 0.5,
            lastUpdated = "2023-07-28T14:50:00.000Z"
        ),
        trending = UICryptoFavoriteTrending.TrendingDown
    ),
    UICryptoFavorite(
        id = 3,
        name = "Litecoin",
        symbol = "LTC",
        price = UIPrice(
            price = 885.75,
            marketCap = 226700000000.0,
            volume24h = 7450000000.0,
            percentChange1h = -0.02,
            percentChange24h = -0.3,
            percentChange7d = 0.5,
            lastUpdated = "2023-07-28T14:50:00.000Z"
        ),
        trending = UICryptoFavoriteTrending.TrendingDown
    ),
)
val uiCryptoPopulars = listOf(
    UICryptoPopular(
        id = 1,
        name = "Bitcoin",
        symbol = "BTC",
        historicalUIPrice = listOf(
            UIPrice(
                price = 30125.50,
                marketCap = 575900000000.0,
                volume24h = 15100000000.0,
                percentChange1h = 0.05,
                percentChange24h = -0.2,
                percentChange7d = 1.3,
                lastUpdated = "2023-07-28T14:50:00.000Z"
            ),
            UIPrice(
                price = 29900.00,
                marketCap = 571000000000.0,
                volume24h = 15000000000.0,
                percentChange1h = 0.1,
                percentChange24h = -0.1,
                percentChange7d = 1.5,
                lastUpdated = "2023-07-27T13:49:00.000Z"
            )
        )
    ),
    UICryptoPopular(
        id = 2,
        name = "Ethereum",
        symbol = "ETH",
        historicalUIPrice = listOf(
            UIPrice(
                price = 1885.75,
                marketCap = 226700000000.0,
                volume24h = 7450000000.0,
                percentChange1h = -0.02,
                percentChange24h = -0.3,
                percentChange7d = 0.5,
                lastUpdated = "2023-07-28T14:50:00.000Z"
            ),
            UIPrice(
                price = 1890.00,
                marketCap = 227000000000.0,
                volume24h = 7500000000.0,
                percentChange1h = 0.2,
                percentChange24h = -0.25,
                percentChange7d = 0.6,
                lastUpdated = "2023-07-27T13:49:00.000Z"
            )
        )
    )
)


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardView() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Kotcoin")
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {

            Text(text = "Favoritos")
            Row {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2)
                ) {

                    items(uiCryptoFavorites) { item ->
                        Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
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
                                    Image(
                                        painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24),
                                        contentDescription = "trending",
                                        modifier = Modifier.size(24.dp),
                                        colorFilter = ColorFilter.tint(Color.Black)
                                    )
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

            Text(text = "Populares")

            Row {

                LazyVerticalGrid(
                    cells = GridCells.Adaptive(minSize = 200.dp)
                ) {

                    items(uiCryptoPopulars) { item ->
                        Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
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
                                Row(verticalAlignment = Alignment.CenterVertically) {

                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    MaterialTheme {
        DashboardView()
    }
}