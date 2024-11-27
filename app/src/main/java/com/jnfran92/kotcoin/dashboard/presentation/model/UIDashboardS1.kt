package com.jnfran92.kotcoin.dashboard.presentation.model

import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice

data class UIDashboardS1(
    val listOfFavorites: List<UICryptoFavorite> = listOf()
) {
    companion object {
        val EMPTY = UIDashboardS1()
        val DUMMY = getDummyData()
        private fun getDummyData(): UIDashboardS1 {
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
            val uiDashboard = UIDashboardS1(
                listOfFavorites = uiCryptoFavorites
            )
            return uiDashboard
        }
    }
}
