package com.jnfran92.domain.model

data class DomainCryptoDetails(
    val id: Long,
    val name: String,
    val symbol: String,
    val slug: String,
    val usdPrices: List<DomainPrice>
)