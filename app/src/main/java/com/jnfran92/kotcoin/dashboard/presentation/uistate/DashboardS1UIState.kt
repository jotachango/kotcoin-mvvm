package com.jnfran92.kotcoin.dashboard.presentation.uistate

import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS1

sealed class DashboardS1UIState {
    object ShowDefaultView : DashboardS1UIState()

    object ShowLoadingView : DashboardS1UIState()

    class ShowErrorRetryView(val t: Throwable) : DashboardS1UIState()

    class ShowDataView(val data: UIDashboardS1) : DashboardS1UIState()

}