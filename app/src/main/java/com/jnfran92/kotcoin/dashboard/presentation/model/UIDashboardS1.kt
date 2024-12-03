package com.jnfran92.kotcoin.dashboard.presentation.model

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
                    usdPrice = 30125.50,
                    trending = UICryptoFavoriteTrending.TrendingUp
                ),
                UICryptoFavorite(
                    id = 2,
                    name = "Ethereum",
                    symbol = "ETH",
                    usdPrice = 1885.75,
                    trending = UICryptoFavoriteTrending.TrendingDown
                ),
                UICryptoFavorite(
                    id = 3,
                    name = "Litecoin",
                    symbol = "LTC",
                    usdPrice = 885.75,
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
