package com.ulstu.api.di

import android.content.Context
import com.ulstu.api.data.repository.DccApiRepositoryImpl
import com.ulstu.api.domain.repository.DccApiRepository
import com.ulstu.shared_prefs.domain.repository.SharedPrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DccApiModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideDccApiRepository(
        @ApplicationContext context: Context,
        dccRetrofitBuilder: Retrofit.Builder,
        sharedPrefsRepository: SharedPrefsRepository,
    ): DccApiRepository {
        return DccApiRepositoryImpl(
            context = context,
            dccRetrofitBuilder = dccRetrofitBuilder,
            sharedPrefsRepository = sharedPrefsRepository,
        )
    }
}