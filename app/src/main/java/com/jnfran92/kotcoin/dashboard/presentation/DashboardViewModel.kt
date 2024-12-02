package com.jnfran92.kotcoin.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnfran92.domain.crypto.GetCryptoDetailsUseCase
import com.jnfran92.domain.crypto.GetCryptoListUseCase
import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavorite
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoFavoriteTrending
import com.jnfran92.kotcoin.dashboard.presentation.model.UICryptoPopular
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS1
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS2
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS1UIState
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS2UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val getCryptoDetailsUseCase: GetCryptoDetailsUseCase
) : ViewModel() {

    private val _viewStateS1 =
        MutableStateFlow<DashboardS1UIState>(DashboardS1UIState.ShowDefaultView)
    val viewStateS1: StateFlow<DashboardS1UIState> = _viewStateS1

    private val _viewStateS2 =
        MutableStateFlow<DashboardS2UIState>(DashboardS2UIState.ShowDefaultView)
    val viewStateS2: StateFlow<DashboardS2UIState> = _viewStateS2

    init {
        Timber.d("init: DashboardViewModel")
        loadData()
    }

    fun loadSection1() {
        Timber.d("loadSection1: ")
        viewModelScope.launch {
            _viewStateS1.value = DashboardS1UIState.ShowLoadingView
            getSection1UseCase()
                .flowOn(Dispatchers.IO)
                .catch {
                    Timber.d("loadSection1: error: $it")
                    _viewStateS1.value = DashboardS1UIState.ShowErrorRetryView(it)
                }
                .collect {
                    Timber.d("loadSection1: success: $it")
                    _viewStateS1.value = DashboardS1UIState.ShowDataView(it)
                }
        }
    }

    fun loadSection2() {
        Timber.d("loadSection2: ")
        viewModelScope.launch {
            _viewStateS2.value = DashboardS2UIState.ShowLoadingView
            getSection2UseCase()
                .flowOn(Dispatchers.IO)
                .catch {
                    Timber.d("loadSection2: error: $it")
                    _viewStateS2.value = DashboardS2UIState.ShowErrorRetryView(it)
                }
                .collect {
                    Timber.d("loadSection2: success: $it")
                    _viewStateS2.value = DashboardS2UIState.ShowDataView(it)
                }
        }
    }

    fun loadData() {
        loadSection1()
        loadSection2()
    }

    fun getSection1UseCase() = flow<UIDashboardS1> {
//        delay(2000)
        Timber.d("getSection1UseCase: ")
        val data = getCryptoListUseCase.invoke().blockingGet()
        Timber.d("getSection1UseCase: data $data")
//        emit(UIDashboardS1.DUMMY)

        val out = data.map {
            UICryptoFavorite(
                id = it.id.toInt(),
                name = it.name,
                symbol = it.symbol,
                price = UIPrice(
                    price = it.usdPrice.price,
                    marketCap = it.usdPrice.marketCap,
                    volume24h = it.usdPrice.volume24h,
                    percentChange1h = it.usdPrice.percentChange1h,
                    percentChange24h = it.usdPrice.percentChange24h,
                    percentChange7d = it.usdPrice.percentChange7d,
                    lastUpdated = it.usdPrice.lastUpdated
                ),
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
    }

    fun getSection2UseCase() = flow<UIDashboardS2> {
//        delay(1000)
//        throw Exception("Error")
//        emit(UIDashboardS2.DUMMY)
        val data = getCryptoListUseCase.invoke().blockingGet().subList(0, 5)
        val out = data.map {
            UICryptoPopular(
                id = it.id.toInt(),
                name = it.name,
                symbol = it.symbol,
                price = UIPrice(
                    price = it.usdPrice.price,
                    marketCap = it.usdPrice.marketCap,
                    volume24h = it.usdPrice.volume24h,
                    percentChange1h = it.usdPrice.percentChange1h,
                    percentChange24h = it.usdPrice.percentChange24h,
                    percentChange7d = it.usdPrice.percentChange7d,
                    lastUpdated = it.usdPrice.lastUpdated
                ),
                trending = if (it.usdPrice.percentChange24h > 0) {
                    UICryptoFavoriteTrending.TrendingUp
                } else {
                    UICryptoFavoriteTrending.TrendingDown
                },
                historicalUIPrice = getCryptoDetailsUseCase.invoke(it.id)
                    .blockingGet().usdPrices.map {
                    UIPrice(
                        price = it.price,
                        marketCap = it.marketCap,
                        volume24h = it.volume24h,
                        percentChange1h = it.percentChange1h,
                        percentChange24h = it.percentChange24h,
                        percentChange7d = it.percentChange7d,
                        lastUpdated = it.lastUpdated
                    )
                }
            )

        }


        emit(
            UIDashboardS2(
                listOfPopular = out
            )
        )
    }
}