package com.jnfran92.kotcoin.dashboard.presentation

import com.jnfran92.domain.crypto.GetCryptoDetailsUseCase
import com.jnfran92.domain.crypto.GetCryptoListUseCase
import com.jnfran92.kotcoin.BuildConfig
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavorite
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavoriteTrending
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoPopular
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS1
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS2
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class DashboardSectionManager @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val getCryptoDetailsUseCase: GetCryptoDetailsUseCase
) {
    fun getSection1() = flow {
        if (!BuildConfig.USE_MOCK_UI) {
            Timber.d("getSection1UseCase: ")
            val data = getCryptoListUseCase.invoke().blockingGet()
            Timber.d("getSection1UseCase: data $data")
            val out = data.map {
                UICryptoFavorite(
                    id = it.id.toInt(),
                    name = it.name,
                    symbol = it.symbol,
                    usdPrice = it.usdPrice.price,
                    trending = if (it.usdPrice.percentChange24h > 0) {
                        UICryptoFavoriteTrending.TrendingUp
                    } else {
                        UICryptoFavoriteTrending.TrendingDown
                    }
                )
            }
            emit(
                UIDashboardS1(
                    listOfFavorites = out
                )
            )
        } else {
            delay(3000)
            emit(UIDashboardS1.DUMMY)
        }
    }

    fun getSection2() = flow {
        if (!BuildConfig.USE_MOCK_UI) {
            val data = getCryptoListUseCase.invoke().blockingGet().subList(0, 40)
            val out = data.map {
                UICryptoPopular(
                    id = it.id.toInt(),
                    name = it.name,
                    symbol = it.symbol,
                    usdPrice = it.usdPrice.price,
                    trending = if (it.usdPrice.percentChange24h > 0) {
                        UICryptoFavoriteTrending.TrendingUp
                    } else {
                        UICryptoFavoriteTrending.TrendingDown
                    },
                    historicalUIPrice = getCryptoDetailsUseCase.invoke(it.id)
                        .blockingGet().usdPrices.map {
                            it.price
                        }
                )

            }
            emit(
                UIDashboardS2(
                    listOfPopular = out
                )
            )
        } else {
            delay(3000)
            emit(UIDashboardS2.DUMMY)
        }
    }
}
