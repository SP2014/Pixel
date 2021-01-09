package com.ashish.pixel.data.remote

import com.ashish.pixel.data.models.PixelImages

interface PixelImageRemote {
    suspend fun getCuratedImages(): PixelImages
}