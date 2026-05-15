package com.servicios.app.di

import com.servicios.app.model.InMemoryWalletRepository
import com.servicios.app.model.WalletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// ─────────────────────────────────────────────────────────────
// AppModule.kt
// Provee dependencias globales (Retrofit, OkHttp, Wallet).
// @InstallIn(SingletonComponent) → viven mientras la app esté activa.
// ─────────────────────────────────────────────────────────────
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /** Repositorio de billetera para manejo de saldo. */
    @Provides
    @Singleton
    fun provideWalletRepository(): WalletRepository = InMemoryWalletRepository()

    /** OkHttp con logs en consola (útil para depurar llamadas API). */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Retrofit listo para conectar con la API REST.
     * TODO: cambiar BASE_URL a la URL real del backend.
     */
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.serviciosapp.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
