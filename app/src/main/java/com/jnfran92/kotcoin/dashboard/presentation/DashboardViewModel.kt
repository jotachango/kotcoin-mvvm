package com.jnfran92.kotcoin.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS1
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS2
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS1UIState
import com.jnfran92.kotcoin.dashboard.presentation.uistate.DashboardS2UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class DashboardViewModel : ViewModel() {

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
        delay(2000)
        emit(UIDashboardS1.DUMMY)
    }

    fun getSection2UseCase() = flow<UIDashboardS2> {
        delay(2000)
//        throw Exception("Error")
        emit(UIDashboardS2.DUMMY)
    }
}