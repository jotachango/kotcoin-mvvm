package com.jnfran92.domain.tissue.crypto

import com.jnfran92.domain.cell.model.DomainPopularCrypto
import com.jnfran92.domain.cell.CryptoCell
import javax.inject.Inject

class GetCryptoPopularListUseCase @Inject constructor(private val cryptoCell: CryptoCell) {
    suspend operator fun invoke(): List<DomainPopularCrypto> {
        return this.cryptoCell.shell.getPopularCryptoList()
    }
}