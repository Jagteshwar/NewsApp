package com.jagteshwar.newsapp.di

import android.app.Application
import com.jagteshwar.newsapp.data.manager.LocalUserManagerImpl
import com.jagteshwar.newsapp.domain.manager.LocalUserManager
import com.jagteshwar.newsapp.domain.usecases.AppEntryUseCases
import com.jagteshwar.newsapp.domain.usecases.ReadAppEntry
import com.jagteshwar.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases {
        return AppEntryUseCases(
            saveAppEntry = SaveAppEntry(localUserManager),
            readAppEntry = ReadAppEntry(localUserManager)
        )
    }
}

