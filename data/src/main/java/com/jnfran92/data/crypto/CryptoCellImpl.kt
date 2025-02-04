package com.jnfran92.data.crypto

import com.jnfran92.data.BuildConfig
import com.jnfran92.domain.cell.CryptoCell
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCellImpl @Inject constructor(
    private val cryptoSurfaceImpl: CryptoSurfaceImpl,
    private val cryptoCoreImpl: CryptoCoreImpl,
    private val cryptoShellImpl: CryptoShellImpl
) : CryptoCell {
    override fun getSurface(): CryptoCell.CryptoSurface {
        return if (!BuildConfig.USE_MOCK_DATA) cryptoSurfaceImpl else cryptoSurfaceImpl.mock
    }

    override fun getShell(): CryptoCell.CryptoShell {
        return if (!BuildConfig.USE_MOCK_DATA) cryptoShellImpl else cryptoShellImpl.mock
    }

    override fun getCore(): CryptoCell.CryptoCore {
        return if (!BuildConfig.USE_MOCK_DATA) cryptoCoreImpl else cryptoCoreImpl.mock
    }
}
