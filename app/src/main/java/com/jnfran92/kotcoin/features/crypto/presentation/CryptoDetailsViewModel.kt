package com.jnfran92.kotcoin.features.crypto.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jnfran92.domain.crypto.GetCryptoDetailsUseCase
import com.jnfran92.kotcoin.features.crypto.presentation.mapper.DomainCryptoDetailsToUIMapper
import com.jnfran92.kotcoin.features.crypto.presentation.model.UICryptoDetails
import com.jnfran92.kotcoin.features.crypto.presentation.uistate.CryptoDetailsUIState
import dagger.hilt.android.scopes.FragmentScoped
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * View Model for handling UI interactions and present data of Crypto detail object.
 * MVI formatted! data flow
 */
@FragmentScoped
class CryptoDetailsViewModel @ViewModelInject constructor(
    private val getCryptoDetailsUseCase: GetCryptoDetailsUseCase,
    private val cryptoDetailsToUIMapper: DomainCryptoDetailsToUIMapper
) : ViewModel() {

    /**
     * RxJava Disposable
     */
    private val compositeDisposable = CompositeDisposable()

    /**
     * view states
     */
    val uiState = MutableLiveData<CryptoDetailsUIState>()

    fun loadData(cryptoId: Long){
        Timber.d("loadData: ")
        uiState.postValue(CryptoDetailsUIState.ShowLoadingView)

        compositeDisposable +=
            getCryptoDetailsUseCase(cryptoId)
                .map(cryptoDetailsToUIMapper::transform)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object: DisposableSingleObserver<UICryptoDetails>(){
                    override fun onSuccess(t: UICryptoDetails) {
                        Timber.d("onSuccess: ")
                        uiState.postValue(CryptoDetailsUIState.ShowDataView(t))
                    }

                    override fun onError(e: Throwable) {
                        Timber.d("onError: ")
                        uiState.postValue(CryptoDetailsUIState.ShowErrorRetryView(e))
                    }
                })
    }

    override fun onCleared() {
        Timber.d("onCleared: ")
        super.onCleared()
        compositeDisposable.dispose()
    }
}
