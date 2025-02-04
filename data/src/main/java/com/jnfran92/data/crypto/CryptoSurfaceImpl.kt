package com.jnfran92.data.crypto

import com.jnfran92.data.crypto.mock.CryptoSurfaceMockImpl
import com.jnfran92.domain.cell.CryptoCell
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoSurfaceImpl @Inject constructor(
    val mock: CryptoSurfaceMockImpl
): CryptoCell.CryptoSurface{
}