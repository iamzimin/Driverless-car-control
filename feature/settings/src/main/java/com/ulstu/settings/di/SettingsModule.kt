package com.ulstu.settings.di

import com.ulstu.settings.data.repository.SettingsRepositoryImpl
import com.ulstu.settings.domain.repository.SettingsRepository
import com.ulstu.shared_prefs.domain.repository.SharedPrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(
        sharedPrefsRepository: SharedPrefsRepository,
    ): SettingsRepository {
        return SettingsRepositoryImpl(
            sharedPrefsRepository = sharedPrefsRepository,
        )
    }
}