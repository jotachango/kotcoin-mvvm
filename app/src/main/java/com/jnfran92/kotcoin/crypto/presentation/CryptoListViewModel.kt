package com.jnfran92.kotcoin.crypto.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnfran92.domain.tissue.crypto.GetCryptoListUseCase
import com.jnfran92.kotcoin.crypto.presentation.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.crypto.presentation.model.UICrypto
import com.jnfran92.kotcoin.crypto.presentation.uistate.CryptoListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


/**
 * View Model for handling UI interactions and present data of Crypto objects
 * MVI formatted! data flow
 */
@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val cryptoToUIMapper: DomainCryptoToUIMapper
) : ViewModel() {

    /**
     * view states
     */
    val uiState = MutableLiveData<CryptoListUIState>()

    fun loadData() {
        Timber.d("loadData: ")
        uiState.postValue(CryptoListUIState.ShowLoadingView)
        viewModelScope.launch {
            try {
                Timber.d("onSuccess: ")
                val data = getCryptoListUseCase()
                    .map(cryptoToUIMapper::transform)
                uiState.postValue(CryptoListUIState.ShowDataView(data))
            } catch (e: Exception) {
                Timber.d("onError: ")
                uiState.postValue(CryptoListUIState.ShowErrorRetryView(e))
            }
        }
    }
}
