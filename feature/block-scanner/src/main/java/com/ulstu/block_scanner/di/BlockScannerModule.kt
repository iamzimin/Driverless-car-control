package com.ulstu.block_scanner.di

import com.ulstu.api.domain.repository.DccApiRepository
import com.ulstu.block_scanner.data.repository.BlockScannerRepositoryImpl
import com.ulstu.block_scanner.domain.repository.BlockScannerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BlockScannerModule {

    @Provides
    @Singleton
    fun provideBlockScannerRepository(
        dccApiRepository: DccApiRepository,
    ): BlockScannerRepository {
        return BlockScannerRepositoryImpl(
            dccApiRepository = dccApiRepository,
        )
    }
}