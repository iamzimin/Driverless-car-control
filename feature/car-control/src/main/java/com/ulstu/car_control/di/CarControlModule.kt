package com.ulstu.car_control.di

import com.ulstu.car_control.data.repository.CarControlRepositoryImpl
import com.ulstu.car_control.domain.repository.CarControlRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarControlModule {

    @Provides
    @Singleton
    fun provideBlockScannerRepository(

    ): CarControlRepository {
        return CarControlRepositoryImpl(

        )
    }
}