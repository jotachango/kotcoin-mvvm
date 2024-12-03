package com.jnfran92.kotcoin.dashboard.presentation.model

import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice

data class UICryptoPopular(
    val id: Int,
    val name: String,
    val symbol: String,
    val usdPrice: Double,
    val trending: UICryptoFavoriteTrending,
    val historicalUIPrice: List<Double>
)