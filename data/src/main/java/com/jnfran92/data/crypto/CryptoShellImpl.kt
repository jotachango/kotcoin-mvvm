package com.jnfran92.data.crypto

import com.jnfran92.data.crypto.datasource.CryptoDataSourceFactory
import com.jnfran92.data.crypto.mapper.CryptoDetailsToDomainMapper
import com.jnfran92.data.crypto.mapper.CryptoToDomainMapper
import com.jnfran92.data.crypto.mock.CryptoShellMockImpl
import com.jnfran92.domain.cell.CryptoCell
import com.jnfran92.domain.cell.model.DomainCrypto
import com.jnfran92.domain.cell.model.DomainCryptoDetails
import com.jnfran92.domain.cell.model.DomainFavoriteCrypto
import com.jnfran92.domain.cell.model.DomainPopularCrypto
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class CryptoShellImpl @Inject constructor(
    private val cryptoDataSourceFactory: CryptoDataSourceFactory,
    private val cryptoToDomainMapper: CryptoToDomainMapper,
    private val cryptoDetailsToDomainMapper: CryptoDetailsToDomainMapper,
    val mock: CryptoShellMockImpl
): CryptoCell.CryptoShell {

    private val dataSource = this.cryptoDataSourceFactory.createRemoteDataSource()
    override suspend fun getFavoriteCryptoList(): List<DomainFavoriteCrypto> {
        val result = dataSource.getCryptoList().blockingGet().map(cryptoToDomainMapper::transform).map {
            DomainFavoriteCrypto(
                id = Random.nextLong(),
                crypto = it
            )
        }
        Timber.d("getFavoriteCryptoList: result $result")
        return result
    }

    override suspend fun getPopularCryptoList(): List<DomainPopularCrypto> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCryptoList(domainCryptoList: List<DomainCrypto>) {
        TODO("Not yet implemented")
    }

    override suspend fun getCryptoList(): List<DomainCrypto> {
        TODO("Not yet implemented")
    }

    override suspend fun getCryptoById(cryptoId: Long): DomainCryptoDetails {
        TODO("Not yet implemented")
    }
}