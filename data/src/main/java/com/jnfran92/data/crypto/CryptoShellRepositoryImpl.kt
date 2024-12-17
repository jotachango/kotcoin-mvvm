package com.jnfran92.data.crypto

import com.jnfran92.data.crypto.datasource.CryptoDataSourceFactory
import com.jnfran92.data.crypto.mapper.CryptoDetailsToDomainMapper
import com.jnfran92.data.crypto.mapper.CryptoToDomainMapper
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
class CryptoShellRepositoryImpl @Inject constructor(
    private val cryptoDataSourceFactory: CryptoDataSourceFactory,
    private val cryptoToDomainMapper: CryptoToDomainMapper,
    private val cryptoDetailsToDomainMapper: CryptoDetailsToDomainMapper
): CryptoCell.CryptoShellRepository {

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
//        val result = dataSource.getCryptoList().flatMap { it.map { crypto -> dataSource.getCryptoById(crypto.id) }  }
//        val cryptoList = dataSource.getCryptoList().blockingGet()
//        val cryptoDetails = cryptoList.map {crypto -> dataSource.getCryptoById(crypto.id).blockingGet() }.map {
//            cryptoDetailsToDomainMapper.transform(it)
//        }
//
//        val result = cryptoDetails.map {
//            DomainPopularCrypto(
//                id = Random.nextLong(),
//                cryptoDetails = cryptoList.map { crypto -> cryptoToDomainMapper.transform(crypto) }.find { domainCrypto -> domainCrypto.id == it.id },
//                it.usdPrices
//            )
//        }
//        Timber.d("getFavoriteCryptoList: result $result")
//        return result
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