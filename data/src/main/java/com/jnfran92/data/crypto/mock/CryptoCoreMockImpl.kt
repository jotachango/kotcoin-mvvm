package com.jnfran92.data.crypto.mock

import com.jnfran92.domain.cell.CryptoCell
import com.jnfran92.domain.cell.model.DomainCrypto
import com.jnfran92.domain.cell.model.DomainPrice
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCoreMockImpl @Inject constructor() : CryptoCell.CryptoCore {
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
    }
}