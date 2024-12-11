package com.jnfran92.kotcoin.crypto.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnfran92.domain.tissue.crypto.GetCryptoDetailsUseCase
import com.jnfran92.kotcoin.crypto.presentation.mapper.DomainCryptoDetailsToUIMapper
import com.jnfran92.kotcoin.crypto.presentation.uistate.CryptoDetailsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * View Model for handling UI interactions and present data of Crypto detail object.
 * MVI formatted! data flow
 */
@HiltViewModel
class CryptoDetailsViewModel @Inject constructor(
    private val getCryptoDetailsUseCase: GetCryptoDetailsUseCase,
    private val cryptoDetailsToUIMapper: DomainCryptoDetailsToUIMapper
) : ViewModel() {

    /**
     * view states
     */
    val uiState = MutableLiveData<CryptoDetailsUIState>()

    fun loadData(cryptoId: Long) {
        Timber.d("loadData: ")
        uiState.postValue(CryptoDetailsUIState.ShowLoadingView)
        viewModelScope.launch {
            try {
                Timber.d("onSuccess: ")
                val data = getCryptoDetailsUseCase(cryptoId).let(cryptoDetailsToUIMapper::transform)
                uiState.postValue(CryptoDetailsUIState.ShowDataView(data))
            } catch (e: Exception) {
                Timber.d("onError: ")
                uiState.postValue(CryptoDetailsUIState.ShowErrorRetryView(e))
            }
        }
    }
}
