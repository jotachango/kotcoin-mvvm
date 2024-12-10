package com.jnfran92.domain.usecase.crypto

import com.jnfran92.domain.model.DomainCryptoDetails
import com.jnfran92.domain.repository.CryptoRepository
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * Get crypto details by id
 */
class GetCryptoDetailsUseCase @Inject constructor(private val repository: CryptoRepository) {
    operator fun invoke(cryptoId: Long): Single<DomainCryptoDetails> {
        Timber.d("invoke: id $cryptoId")
        return this.repository.getCryptoById(cryptoId)
    }
}