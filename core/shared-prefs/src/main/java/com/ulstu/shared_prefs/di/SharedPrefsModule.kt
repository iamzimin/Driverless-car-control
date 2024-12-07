package com.ulstu.shared_prefs.di

import android.content.Context
import com.ulstu.shared_prefs.data.repository.SharedPrefsRepositoryImpl
import com.ulstu.shared_prefs.domain.repository.SharedPrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {
    @Provides
    @Singleton
    fun provideSharedPrefsRepository(
        @ApplicationContext context: Context,
    ): SharedPrefsRepository {
        return SharedPrefsRepositoryImpl(
            context = context,
        )
    }
}