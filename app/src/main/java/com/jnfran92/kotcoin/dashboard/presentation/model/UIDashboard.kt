package com.jnfran92.kotcoin.dashboard.presentation.model

import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

data class UIDashboard(
    val listOfFavorites: List<UICryptoFavorite> = listOf(),
    val listOfPopular: List<UICryptoPopular> = listOf()
) {
    companion object {
        val EMPTY = UIDashboard()
        val DUMMY = getDummyData()
    }
}

fun getDummyData(): UIDashboard {

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
            historicalUIPrice = List(20) { index ->
                val value = sin(2 * PI * index / 20)
                UIPrice(
                    price = value,
                    marketCap = 226700000000.0,
                    volume24h = 7450000000.0,
                    percentChange1h = -0.02,
                    percentChange24h = -0.3,
                    percentChange7d = 0.5,
                    lastUpdated = "2023-07-28T14:50:00.000Z"
                )
            }
        ),
        UICryptoPopular(
            id = 2,
            name = "Ethereum",
            symbol = "ETH",
            historicalUIPrice = List(20) { index ->
                val value = sin(2 * PI * index / 20).pow(2)
                UIPrice(
                    price = value,
                    marketCap = 226700000000.0,
                    volume24h = 7450000000.0,
                    percentChange1h = -0.02,
                    percentChange24h = -0.3,
                    percentChange7d = 0.5,
                    lastUpdated = "2023-07-28T14:50:00.000Z"
                )
            }
        ),
        UICryptoPopular(
            id = 2,
            name = "Another",
            symbol = "ANT",
            historicalUIPrice = List(20) { index ->
                val value = tan(2 * PI * index / 20).pow(2)
                UIPrice(
                    price = value,
                    marketCap = 226700000000.0,
                    volume24h = 7450000000.0,
                    percentChange1h = -0.02,
                    percentChange24h = -0.3,
                    percentChange7d = 0.5,
                    lastUpdated = "2023-07-28T14:50:00.000Z"
                )
            }
        )
    )
    val uiDashboard = UIDashboard(
        listOfFavorites = uiCryptoFavorites,
        listOfPopular = uiCryptoPopulars
    )
    return uiDashboard
}