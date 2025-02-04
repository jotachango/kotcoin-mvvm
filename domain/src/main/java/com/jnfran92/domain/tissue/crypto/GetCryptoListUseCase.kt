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
            .getCore()
            .getCryptoList()
            .let { cryptoCell.getShell().saveCryptoList(it) }
            .let { cryptoCell.getShell().getCryptoList() }
    }
}