package com.jnfran92.kotcoin.dashboard.presentation.model

import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

data class UIDashboardS2(
    val listOfPopular: List<UICryptoPopular> = listOf()
) {
    companion object {
        val EMPTY = UIDashboardS2()
        val DUMMY = getDummyData()
        private fun getDummyData(): UIDashboardS2 {

            val uiCryptoPopulars = listOf(
                UICryptoPopular(
                    id = 1,
                    name = "Bitcoin",
                    symbol = "BTC",
                    usdPrice = 30125.50,
                    trending = UICryptoFavoriteTrending.TrendingUp,
                    historicalUIPrice = List(20) { index ->
                        val value = sin(2 * PI * index / 20)
                        value
                    }
                ),
                UICryptoPopular(
                    id = 2,
                    name = "Ethereum",
                    symbol = "ETH",
                    usdPrice = 30125.50,
                    trending = UICryptoFavoriteTrending.TrendingUp,
                    historicalUIPrice = List(20) { index ->
                        val value = sin(2 * PI * index / 20).pow(2)
                        value
                    }
                ),
                UICryptoPopular(
                    id = 2,
                    name = "Another",
                    symbol = "ANT",
                    usdPrice = 30125.50,
                    trending = UICryptoFavoriteTrending.TrendingUp,
                    historicalUIPrice = List(20) { index ->
                        val value = tan(2 * PI * index / 20).pow(2)
                        value
                    }
                )
            )
            val uiDashboard = UIDashboardS2(
                listOfPopular = uiCryptoPopulars
            )
            return uiDashboard
        }
    } }
