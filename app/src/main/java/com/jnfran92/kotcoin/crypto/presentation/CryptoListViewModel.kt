package com.jnfran92.kotcoin.crypto.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jnfran92.domain.usecase.crypto.GetCryptoListUseCase
import com.jnfran92.kotcoin.crypto.presentation.mapper.DomainCryptoToUIMapper
import com.jnfran92.kotcoin.crypto.presentation.model.UICrypto
import com.jnfran92.kotcoin.crypto.presentation.uistate.CryptoListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
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
     * RxJava Disposable
     */
    private val compositeDisposable = CompositeDisposable()

    /**
     * view states
     */
    val uiState = MutableLiveData<CryptoListUIState>()

    fun loadData(){
        Timber.d("loadData: ")
        uiState.postValue(CryptoListUIState.ShowLoadingView)

        compositeDisposable +=
            getCryptoListUseCase()
                .map(cryptoToUIMapper::transform)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object: DisposableSingleObserver<List<UICrypto>>(){
                    override fun onSuccess(t: List<UICrypto>) {
                        Timber.d("onSuccess: ")
                        uiState.postValue(CryptoListUIState.ShowDataView(t))
                    }

                    override fun onError(e: Throwable) {
                        Timber.d("onError: ")
                        uiState.postValue(CryptoListUIState.ShowErrorRetryView(e))
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
