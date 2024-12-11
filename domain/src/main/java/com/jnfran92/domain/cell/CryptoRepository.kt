package com.jnfran92.domain.cell

import com.jnfran92.domain.cell.model.DomainCrypto
import com.jnfran92.domain.cell.model.DomainCryptoDetails
import io.reactivex.Single


/**
 *  Business Logic contract for Managing DomainCrypto objects.
 */
interface CryptoRepository {

    /**
     * Get List of DomainCrypto objects.
     */
    fun getCryptoList(): Single<List<DomainCrypto>>

    /**
     * Get DomainCrypto by Id
     */
    fun getCryptoById(cryptoId: Long): Single<DomainCryptoDetails>

}