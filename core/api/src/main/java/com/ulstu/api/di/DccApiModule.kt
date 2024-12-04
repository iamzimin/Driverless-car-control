package com.ulstu.api.di

import android.content.Context
import com.ulstu.api.data.repository.DccApiRepositoryImpl
import com.ulstu.api.domain.repository.DccApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DccApiModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideDccApiRepository(
        context: Context,
        dccRetrofitBuilder: Retrofit.Builder,
    ): DccApiRepository {
        return DccApiRepositoryImpl(
            context = context,
            dccRetrofitBuilder = dccRetrofitBuilder,
        )
    }
}