package com.jagteshwar.newsapp.di

import android.app.Application
import com.jagteshwar.newsapp.data.manager.LocalUserManagerImpl
import com.jagteshwar.newsapp.data.remote.NewsApi
import com.jagteshwar.newsapp.data.repository.NewsRepositoryImpl
import com.jagteshwar.newsapp.domain.manager.LocalUserManager
import com.jagteshwar.newsapp.domain.repository.NewsRepository
import com.jagteshwar.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.jagteshwar.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.jagteshwar.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.jagteshwar.newsapp.domain.usecases.news.GetNewsUseCase
import com.jagteshwar.newsapp.domain.usecases.news.NewsUseCases
import com.jagteshwar.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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


    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(api:NewsApi): NewsRepository = NewsRepositoryImpl(api)

    @Provides
    @Singleton
    fun providesNewsUseCases(repository: NewsRepository): NewsUseCases{
        return NewsUseCases(
            getNewsUseCase = GetNewsUseCase(repository)
        )
    }
}

