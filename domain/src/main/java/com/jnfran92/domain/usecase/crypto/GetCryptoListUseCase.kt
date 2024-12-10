package com.jnfran92.domain.usecase.crypto

import com.jnfran92.domain.model.DomainCrypto
import com.jnfran92.domain.repository.CryptoRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Get data
 */
class GetCryptoListUseCase @Inject constructor(private val repository: CryptoRepository) {
    operator fun invoke(): Single<List<DomainCrypto>> {
        return this.repository.getCryptoList()
    }
}