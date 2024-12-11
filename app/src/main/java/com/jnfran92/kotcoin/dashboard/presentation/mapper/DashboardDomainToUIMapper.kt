package com.jnfran92.kotcoin.dashboard.presentation.mapper

import com.jnfran92.domain.cell.model.DomainCrypto
import com.jnfran92.domain.cell.model.DomainCryptoDetails
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavorite
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavoriteTrending
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoPopular
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS1
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS2
import javax.inject.Inject

class DashboardDomainToUIMapper @Inject constructor() {

    fun transform(domainCryptoList: List<DomainCrypto>): UIDashboardS1 {
        return UIDashboardS1(
            listOfFavorites = domainCryptoList.map { domainCrypto ->
                UICryptoFavorite(
                    id = domainCrypto.id.toInt(),
                    name = domainCrypto.name,
                    symbol = domainCrypto.symbol,
                    usdPrice = domainCrypto.usdPrice.price,
                    trending = if (domainCrypto.usdPrice.percentChange1h > 0) {
                        UICryptoFavoriteTrending.TrendingUp
                    } else {
                        UICryptoFavoriteTrending.TrendingDown
                    }
                )
            }
        )
    }


    fun transform(
        domainCryptoList: List<DomainCrypto>,
        domainCryptoDetailsList: List<DomainCryptoDetails>
    ): UIDashboardS2 {
        return UIDashboardS2(
            listOfPopular = domainCryptoList.map { domainCrypto ->
                UICryptoPopular(
                    id = domainCrypto.id.toInt(),
                    name = domainCrypto.name,
                    symbol = domainCrypto.symbol,
                    usdPrice = domainCrypto.usdPrice.price,
                    trending = if (domainCrypto.usdPrice.percentChange1h > 0) {
                        UICryptoFavoriteTrending.TrendingUp
                    } else {
                        UICryptoFavoriteTrending.TrendingDown
                    },
                    historicalUIPrice = domainCryptoDetailsList.find { it.id == domainCrypto.id }?.usdPrices?.map {
                        it.price
                    }.orEmpty()
                )
            }
        )
    }
}
