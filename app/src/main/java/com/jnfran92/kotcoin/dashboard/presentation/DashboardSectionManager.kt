package com.jnfran92.kotcoin.dashboard.presentation

import com.jnfran92.domain.crypto.GetCryptoDetailsUseCase
import com.jnfran92.domain.crypto.GetCryptoListUseCase
import com.jnfran92.kotcoin.BuildConfig
import com.jnfran92.kotcoin.dashboard.presentation.mapper.DashboardDomainToUIMapper
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS1
import com.jnfran92.kotcoin.dashboard.presentation.model.UIDashboardS2
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardSectionManager @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase,
    private val getCryptoDetailsUseCase: GetCryptoDetailsUseCase,
    private val dashboardDomainToUIMapper: DashboardDomainToUIMapper
) {
    fun getSection1() = flow {
        if (!BuildConfig.USE_MOCK_UI) {
            val domainCryptoList = getCryptoListUseCase.invoke().blockingGet()
            emit(
                dashboardDomainToUIMapper.transform(
                    domainCryptoList = domainCryptoList
                )
            )
        } else {
            delay(DUMMY_DELAY_MS)
            emit(UIDashboardS1.DUMMY)
        }
    }

    fun getSection2() = flow {
        if (!BuildConfig.USE_MOCK_UI) {
            val domainCryptoList = getCryptoListUseCase.invoke().blockingGet()
            val domainCryptoDetailsList = domainCryptoList.map {
                getCryptoDetailsUseCase.invoke(it.id).blockingGet()
            }
            emit(
                dashboardDomainToUIMapper.transform(
                    domainCryptoList = domainCryptoList,
                    domainCryptoDetailsList = domainCryptoDetailsList
                )
            )
        } else {
            delay(DUMMY_DELAY_MS)
            emit(UIDashboardS2.DUMMY)
        }
    }

    companion object {
        const val DUMMY_DELAY_MS = 3000L
    }
}
