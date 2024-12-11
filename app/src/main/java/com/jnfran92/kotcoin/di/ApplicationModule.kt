package com.jnfran92.kotcoin.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.jnfran92.data.crypto.CryptoRepositoryImpl
import com.jnfran92.data.crypto.datasource.CryptoDataSourceFactory
import com.jnfran92.data.crypto.mapper.CryptoDetailsToDomainMapper
import com.jnfran92.data.crypto.mapper.CryptoToDomainMapper
import com.jnfran92.data.crypto.source.crypto.cache.CryptoCacheSupplier
import com.jnfran92.data.crypto.source.crypto.cache.CryptoCacheSupplierImpl
import com.jnfran92.data.crypto.source.crypto.local.CryptoDao
import com.jnfran92.data.crypto.source.crypto.remote.CryptoRemoteSupplier
import com.jnfran92.data.crypto.source.crypto.remote.CryptoRemoteSupplierImpl
import com.jnfran92.data.crypto.source.db.AppDatabase
import com.jnfran92.domain.cell.CryptoCell
import com.jnfran92.domain.cell.CryptoCell.CryptoSurfaceRepository
import com.jnfran92.domain.cell.CryptoRepository
import com.jnfran92.domain.cell.model.DomainCrypto
import com.jnfran92.domain.cell.model.DomainCryptoDetails
import com.jnfran92.domain.cell.model.DomainFavoriteCrypto
import com.jnfran92.domain.cell.model.DomainPopularCrypto
import com.jnfran92.kotcoin.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    private const val BASE_URL = "https://pro-api.coinmarketcap.com/"


    @Provides
    @Singleton
    fun cryptoRepository(cryptoRepository: CryptoRepositoryImpl): CryptoRepository {
        return cryptoRepository
    }

    @Provides
    @Singleton
    fun cryptoApi(cryptoApi: CryptoRemoteSupplierImpl): CryptoRemoteSupplier {
        return cryptoApi
    }

    @Provides
    @Singleton
    fun cryptoCache(cryptoCache: CryptoCacheSupplierImpl): CryptoCacheSupplier {
        return cryptoCache
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("X-CMC_PRO_API_KEY", BuildConfig.API_TOKEN)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "app-database"
        ).build()
    }

    @Provides
    @Singleton
    fun cryptoDao(appDatabase: AppDatabase): CryptoDao {
        return appDatabase.cryptoDao()
    }

    @Provides
    @Singleton
    fun cryptoCell(
        cryptoDataSourceFactory: CryptoDataSourceFactory,
        cryptoToDomainMapper: CryptoToDomainMapper,
        cryptoDetailsToDomainMapper: CryptoDetailsToDomainMapper
    ): CryptoCell {
        return CryptoCell(
            surface = object : CryptoSurfaceRepository {},
            shell = object : CryptoCell.CryptoShellRepository {
                override suspend fun getFavoriteCryptoList(): List<DomainFavoriteCrypto> {
                    TODO("Not yet implemented")
                }

                override suspend fun getPopularCryptoList(): List<DomainPopularCrypto> {
                    TODO("Not yet implemented")
                }

                override suspend fun saveCryptoList(domainCryptoList: List<DomainCrypto>) {
//                   cryptoDataSourceFactory.createLocalDataSource().saveCryptoList(domainCryptoList.let(cryptoToDomainMapper::transform)).blockingGet()
                }

                override suspend fun getCryptoList(): List<DomainCrypto> {
                    return cryptoDataSourceFactory.createLocalDataSource().getCryptoList()
                        .blockingGet().map(
                            cryptoToDomainMapper::transform
                        )
                }

                override suspend fun getCryptoById(cryptoId: Long): DomainCryptoDetails {
                    return cryptoDataSourceFactory.createLocalDataSource().getCryptoById(cryptoId)
                        .blockingGet().let(
                            cryptoDetailsToDomainMapper::transform
                        )
                }

            },
            core = object : CryptoCell.CryptoCoreRepository {
                override suspend fun getCryptoList(): List<DomainCrypto> {
                    return cryptoDataSourceFactory.createRemoteDataSource().getCryptoList()
                        .blockingGet().map(cryptoToDomainMapper::transform)
                }

            }
        )
    }
}