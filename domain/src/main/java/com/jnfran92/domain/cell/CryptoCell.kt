package com.jnfran92.domain.cell

import com.jnfran92.domain.cell.model.DomainCrypto
import com.jnfran92.domain.cell.model.DomainCryptoDetails
import com.jnfran92.domain.cell.model.DomainFavoriteCrypto
import com.jnfran92.domain.cell.model.DomainPopularCrypto

interface CryptoCell {

    fun getSurface(): CryptoSurface
    fun getShell(): CryptoShell
    fun getCore(): CryptoCore

    interface CryptoSurface

    interface CryptoShell {
        suspend fun getFavoriteCryptoList(): List<DomainFavoriteCrypto>
        suspend fun getPopularCryptoList(): List<DomainPopularCrypto>
        suspend fun saveCryptoList(domainCryptoList: List<DomainCrypto>)
        suspend fun getCryptoList(): List<DomainCrypto>
        suspend fun getCryptoById(cryptoId: Long): DomainCryptoDetails
    }

    interface CryptoCore {
        suspend fun getCryptoList(): List<DomainCrypto>
    }
}
