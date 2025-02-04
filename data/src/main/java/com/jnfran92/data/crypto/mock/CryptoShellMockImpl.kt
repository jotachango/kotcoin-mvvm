package com.jnfran92.data.crypto.mock

import com.jnfran92.domain.cell.CryptoCell
import com.jnfran92.domain.cell.model.DomainCrypto
import com.jnfran92.domain.cell.model.DomainCryptoDetails
import com.jnfran92.domain.cell.model.DomainFavoriteCrypto
import com.jnfran92.domain.cell.model.DomainPopularCrypto
import com.jnfran92.domain.cell.model.DomainPrice
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoShellMockImpl @Inject constructor() : CryptoCell.CryptoShell {
    override suspend fun getFavoriteCryptoList(): List<DomainFavoriteCrypto> {
        return listOf(
            DomainFavoriteCrypto(
                id = 1L,
                crypto = DomainCrypto(
                    id = 1L,
                    name = "Bitcoin",
                    symbol = "BTC",
                    slug = "bitcoin",
                    tags = listOf("cryptocurrency", "decentralized", "store-of-value"),
                    cmcRank = 1,
                    circulatingSupply = 19_000_000.0,
                    totalSupply = 21_000_000.0,
                    maxSupply = 21_000_000.0,
                    usdPrice = DomainPrice(
                        price = 42000.0,
                        marketCap = 798000000000.0,
                        volume24h = 50000000000.0,
                        percentChange1h = -0.5,
                        percentChange24h = 2.1,
                        percentChange7d = 5.3,
                        lastUpdated = "2025-02-04T12:00:00Z"
                    )
                )
            ),
            DomainFavoriteCrypto(
                id = 2L,
                crypto = DomainCrypto(
                    id = 2L,
                    name = "Ethereum",
                    symbol = "ETH",
                    slug = "ethereum",
                    tags = listOf("smart-contracts", "defi", "nft"),
                    cmcRank = 2,
                    circulatingSupply = 120_000_000.0,
                    totalSupply = 120_000_000.0,
                    maxSupply = 0.0,
                    usdPrice = DomainPrice(
                        price = 2800.0,
                        marketCap = 336000000000.0,
                        volume24h = 20000000000.0,
                        percentChange1h = 0.2,
                        percentChange24h = 1.8,
                        percentChange7d = 4.7,
                        lastUpdated = "2025-02-04T12:00:00Z"
                    )
                )
            )
        )
    }


    override suspend fun getPopularCryptoList(): List<DomainPopularCrypto> {
        return listOf(
            DomainPopularCrypto(
                id = 1L,
                cryptoDetails = DomainCrypto(
                    id = 1L,
                    name = "Bitcoin",
                    symbol = "BTC",
                    slug = "bitcoin",
                    tags = listOf("cryptocurrency", "decentralized", "store-of-value"),
                    cmcRank = 1,
                    circulatingSupply = 19_000_000.0,
                    totalSupply = 21_000_000.0,
                    maxSupply = 21_000_000.0,
                    usdPrice = DomainPrice(
                        price = 42000.0,
                        marketCap = 798000000000.0,
                        volume24h = 50000000000.0,
                        percentChange1h = -0.5,
                        percentChange24h = 2.1,
                        percentChange7d = 5.3,
                        lastUpdated = "2025-02-04T12:00:00Z"
                    )
                ),
                historicalPrices = listOf(
                    DomainPrice(
                        41000.0,
                        780000000000.0,
                        48000000000.0,
                        -0.8,
                        1.5,
                        4.2,
                        "2025-02-03T12:00:00Z"
                    ),
                    DomainPrice(
                        40500.0,
                        770000000000.0,
                        47000000000.0,
                        -1.0,
                        0.8,
                        3.5,
                        "2025-02-02T12:00:00Z"
                    )
                )
            ),
            DomainPopularCrypto(
                id = 2L,
                cryptoDetails = DomainCrypto(
                    id = 2L,
                    name = "Ethereum",
                    symbol = "ETH",
                    slug = "ethereum",
                    tags = listOf("smart-contracts", "defi", "nft"),
                    cmcRank = 2,
                    circulatingSupply = 120_000_000.0,
                    totalSupply = 120_000_000.0,
                    maxSupply = 0.0, // No tiene límite máximo
                    usdPrice = DomainPrice(
                        price = 2800.0,
                        marketCap = 336000000000.0,
                        volume24h = 20000000000.0,
                        percentChange1h = 0.2,
                        percentChange24h = 1.8,
                        percentChange7d = 4.7,
                        lastUpdated = "2025-02-04T12:00:00Z"
                    )
                ),
                historicalPrices = listOf(
                    DomainPrice(
                        2750.0,
                        330000000000.0,
                        19000000000.0,
                        0.1,
                        1.2,
                        3.8,
                        "2025-02-03T12:00:00Z"
                    ),
                    DomainPrice(
                        2700.0,
                        320000000000.0,
                        18000000000.0,
                        -0.3,
                        0.7,
                        3.1,
                        "2025-02-02T12:00:00Z"
                    )
                )
            )
        )
    }

    override suspend fun saveCryptoList(domainCryptoList: List<DomainCrypto>) {
        Timber.d("saveCryptoList: Se han guardado ${domainCryptoList.size} criptomonedas en la base de datos.")
    }

    override suspend fun getCryptoList(): List<DomainCrypto> {
        return listOf(
            DomainCrypto(
                id = 1L,
                name = "Bitcoin",
                symbol = "BTC",
                slug = "bitcoin",
                tags = listOf("cryptocurrency", "decentralized", "store-of-value"),
                cmcRank = 1,
                circulatingSupply = 19_000_000.0,
                totalSupply = 21_000_000.0,
                maxSupply = 21_000_000.0,
                usdPrice = DomainPrice(
                    price = 42000.0,
                    marketCap = 798000000000.0,
                    volume24h = 50000000000.0,
                    percentChange1h = -0.5,
                    percentChange24h = 2.1,
                    percentChange7d = 5.3,
                    lastUpdated = "2025-02-04T12:00:00Z"
                )
            ),
            DomainCrypto(
                id = 2L,
                name = "Ethereum",
                symbol = "ETH",
                slug = "ethereum",
                tags = listOf("smart-contracts", "defi", "nft"),
                cmcRank = 2,
                circulatingSupply = 120_000_000.0,
                totalSupply = 120_000_000.0,
                maxSupply = 0.0, // No tiene límite máximo
                usdPrice = DomainPrice(
                    price = 2800.0,
                    marketCap = 336000000000.0,
                    volume24h = 20000000000.0,
                    percentChange1h = 0.2,
                    percentChange24h = 1.8,
                    percentChange7d = 4.7,
                    lastUpdated = "2025-02-04T12:00:00Z"
                )
            )
        )
    }


    override suspend fun getCryptoById(cryptoId: Long): DomainCryptoDetails {
        val cryptoDetailsMap = mapOf(
            1L to DomainCryptoDetails(
                id = 1L,
                name = "Bitcoin",
                symbol = "BTC",
                slug = "bitcoin",
                usdPrices = listOf(
                    DomainPrice(
                        42000.0,
                        798000000000.0,
                        50000000000.0,
                        -0.5,
                        2.1,
                        5.3,
                        "2025-02-04T12:00:00Z"
                    ),
                    DomainPrice(
                        41000.0,
                        780000000000.0,
                        48000000000.0,
                        -0.8,
                        1.5,
                        4.2,
                        "2025-02-03T12:00:00Z"
                    ),
                    DomainPrice(
                        40500.0,
                        770000000000.0,
                        47000000000.0,
                        -1.0,
                        0.8,
                        3.5,
                        "2025-02-02T12:00:00Z"
                    )
                )
            ),
            2L to DomainCryptoDetails(
                id = 2L,
                name = "Ethereum",
                symbol = "ETH",
                slug = "ethereum",
                usdPrices = listOf(
                    DomainPrice(
                        2800.0,
                        336000000000.0,
                        20000000000.0,
                        0.2,
                        1.8,
                        4.7,
                        "2025-02-04T12:00:00Z"
                    ),
                    DomainPrice(
                        2750.0,
                        330000000000.0,
                        19000000000.0,
                        0.1,
                        1.2,
                        3.8,
                        "2025-02-03T12:00:00Z"
                    ),
                    DomainPrice(
                        2700.0,
                        320000000000.0,
                        18000000000.0,
                        -0.3,
                        0.7,
                        3.1,
                        "2025-02-02T12:00:00Z"
                    )
                )
            )
        )
        return cryptoDetailsMap[cryptoId]
            ?: throw IllegalArgumentException("Crypto with ID $cryptoId not found")
    }
}
