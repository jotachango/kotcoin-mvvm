package com.jnfran92.domain.cell.model

data class DomainPopularCrypto(
    val id: Long,
    var cryptoDetails: DomainCrypto,
    var historicalPrices: List<DomainPrice>
)
