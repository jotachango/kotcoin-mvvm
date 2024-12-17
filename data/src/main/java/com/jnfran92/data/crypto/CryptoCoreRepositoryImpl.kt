package com.jnfran92.data.crypto

import com.jnfran92.data.crypto.datasource.CryptoDataSourceFactory
import com.jnfran92.data.crypto.mapper.CryptoToDomainMapper
import com.jnfran92.domain.cell.CryptoCell
import com.jnfran92.domain.cell.model.DomainCrypto
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCoreRepositoryImpl @Inject constructor(
    private val cryptoDataSourceFactory: CryptoDataSourceFactory,
    private val cryptoToDomainMapper: CryptoToDomainMapper,
) : CryptoCell.CryptoCoreRepository {

    private val dataSource = this.cryptoDataSourceFactory.createRemoteDataSource()
    override suspend fun getCryptoList(): List<DomainCrypto> {
        val result = dataSource
            .getCryptoList()
            .blockingGet()
            .map(cryptoToDomainMapper::transform)
        Timber.d("getCryptoList: result $result")
        return result
    }
}