package com.jnfran92.kotcoin.features.crypto.presentation.uistate

import com.jnfran92.kotcoin.features.crypto.presentation.model.UICryptoDetails

sealed class CryptoDetailsUIState {

    object ShowDefaultView : CryptoDetailsUIState()

    object ShowLoadingView : CryptoDetailsUIState()

    class ShowErrorRetryView(val t: Throwable) : CryptoDetailsUIState()

    class ShowDataView(val data: UICryptoDetails) : CryptoDetailsUIState()
}
