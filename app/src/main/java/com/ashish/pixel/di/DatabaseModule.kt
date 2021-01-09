package com.ashish.pixel.di

import android.app.Application
import com.ashish.pixel.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = AppDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePixelImageDao(database: AppDatabase) = database.pixelImageDao()
}