package com.jnfran92.kotcoin.dashboard.presentation.model

import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice

data class UICryptoFavorite(
    val id: Int,
    val name: String,
    val symbol: String,
    val price: UIPrice,
    val trending: UICryptoFavoriteTrending
)