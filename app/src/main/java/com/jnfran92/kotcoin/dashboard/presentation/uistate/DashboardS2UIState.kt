package com.jnfran92.kotcoin.dashboard.presentation.uistate

import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS2

sealed class DashboardS2UIState {
    object ShowDefaultView : DashboardS2UIState()

    object ShowLoadingView : DashboardS2UIState()

    class ShowErrorRetryView(val t: Throwable) : DashboardS2UIState()

    class ShowDataView(val data: UIDashboardS2) : DashboardS2UIState()

}