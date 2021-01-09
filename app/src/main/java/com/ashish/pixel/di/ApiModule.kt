package com.ashish.pixel.di

import com.ashish.pixel.data.remote.HttpRequestInterceptor
import com.ashish.pixel.data.remote.PixelImageRemote
import com.ashish.pixel.data.remote.PixelImageRemoteImpl
import com.ashish.pixel.data.remote.PixelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(okHttpClient: OkHttpClient): PixelService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.pexels.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PixelService::class.java)

    @Singleton
    @Provides
    fun providePixelImageRemote(pixelService: PixelService): PixelImageRemote = PixelImageRemoteImpl(pixelService)
}