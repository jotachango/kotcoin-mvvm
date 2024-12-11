package com.jnfran92.domain.tissue.crypto

import com.jnfran92.domain.cell.model.DomainFavoriteCrypto
import com.jnfran92.domain.cell.CryptoCell
import javax.inject.Inject


class GetCryptoFavoriteListUseCase @Inject constructor(private val cryptoCell: CryptoCell) {
    suspend operator fun invoke(): List<DomainFavoriteCrypto> {
        return this.cryptoCell.shell.getFavoriteCryptoList()
    }
}