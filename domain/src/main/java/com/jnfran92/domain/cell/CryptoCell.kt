package com.jnfran92.domain.cell

import com.jnfran92.domain.cell.model.DomainCrypto
import com.jnfran92.domain.cell.model.DomainCryptoDetails
import com.jnfran92.domain.cell.model.DomainFavoriteCrypto
import com.jnfran92.domain.cell.model.DomainPopularCrypto

class CryptoCell(
    val surface: CryptoSurfaceRepository,
    val shell: CryptoShellRepository,
    val core: CryptoCoreRepository
) {

    interface CryptoSurfaceRepository

    interface CryptoShellRepository {
        suspend fun getFavoriteCryptoList(): List<DomainFavoriteCrypto>
        suspend fun getPopularCryptoList(): List<DomainPopularCrypto>
        suspend fun saveCryptoList(domainCryptoList: List<DomainCrypto>)
        suspend fun getCryptoList(): List<DomainCrypto>
        suspend fun getCryptoById(cryptoId: Long): DomainCryptoDetails
    }

    interface CryptoCoreRepository {
        suspend fun getCryptoList(): List<DomainCrypto>
    }
}
