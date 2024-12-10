package com.jnfran92.kotcoin.crypto.presentation.mapper

import com.jnfran92.domain.model.DomainCrypto
import com.jnfran92.kotcoin.crypto.presentation.model.UICrypto
import com.jnfran92.kotcoin.crypto.presentation.model.UIPrice
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainCryptoToUIMapper @Inject constructor() {

    fun transform(item: DomainCrypto): UICrypto {
        return UICrypto(
            id = item.id,
            slug = item.slug,
            symbol = item.symbol,
            name = item.name,
            totalSupply = item.totalSupply,
            tags = item.tags,
            maxSupply = item.maxSupply,
            cmcRank = item.cmcRank,
            circulatingSupply = item.circulatingSupply,
            usdPrice = with(item.usdPrice) {
                UIPrice(
                    price = price,
                    marketCap = marketCap,
                    volume24h = volume24h,
                    percentChange1h = volume24h,
                    percentChange24h = percentChange24h,
                    percentChange7d = percentChange7d,
                    lastUpdated = lastUpdated
                )
            }
        )
    }

    fun transform(items: List<DomainCrypto>): List<UICrypto> = items.map(::transform)
}