package com.ashish.pixel.data.remote

import com.ashish.pixel.data.models.PexelResponse
import retrofit2.http.GET

interface PixelService {
    @GET("v1/curated?per_page=20")
    suspend fun getCuratedImages(): PexelResponse
}