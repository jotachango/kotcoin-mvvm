package com.jnfran92.domain.tissue.crypto

import com.jnfran92.domain.cell.CryptoCell
import com.jnfran92.domain.cell.model.DomainCryptoDetails
import com.jnfran92.domain.cell.CryptoRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * Get crypto details by id
 */
class GetCryptoDetailsUseCase @Inject constructor(private val cryptoCell: CryptoCell) {
    suspend operator fun invoke(cryptoId: Long): DomainCryptoDetails {
        Timber.d("invoke: id $cryptoId")
        return cryptoCell.shell.getCryptoById(cryptoId)
    }
}