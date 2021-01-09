package com.ashish.pixel.data.remote

import com.ashish.pixel.data.models.PixelImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PixelImageRemoteImpl(private val pixelService: PixelService): PixelImageRemote {
    override suspend fun getCuratedImages() = withContext(Dispatchers.Default){
        val response = pixelService.getCuratedImages()
        val photos = response.photos
        photos.mapNotNull {
            PixelImage(it.id, it.photographer, it.photographerUrl, it.src.medium)
        }
    }
}