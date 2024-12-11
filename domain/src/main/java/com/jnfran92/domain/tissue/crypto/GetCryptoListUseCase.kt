package com.jnfran92.domain.tissue.crypto

import com.jnfran92.domain.cell.CryptoCell
import com.jnfran92.domain.cell.model.DomainCrypto
import javax.inject.Inject

/**
 * Get data
 */
class GetCryptoListUseCase @Inject constructor(private val cryptoCell: CryptoCell) {
    suspend operator fun invoke(): List<DomainCrypto> {
        return cryptoCell
            .core
            .getCryptoList()
            .let { cryptoCell.shell.saveCryptoList(it) }
            .let { cryptoCell.shell.getCryptoList() }
    }
}