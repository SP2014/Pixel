package com.ashish.pixel.data.repository

import com.ashish.pixel.data.local.PixelImageDao
import com.ashish.pixel.data.models.PixelImages
import com.ashish.pixel.data.remote.PixelImageRemote
import com.ashish.pixel.data.remote.PixelService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.ashish.pixel.data.models.Result
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class PixelRepository @Inject constructor(
    private val pixelImageDao: PixelImageDao,
    private val remote: PixelImageRemote
) {
    fun getCuratedImages(): Flow<Result<PixelImages>> = flow {
        emit(Result.loading())

        val images = remote.getCuratedImages()

        emit(Result.successOrEmpty(images))
    }.catch {
        e -> emit(Result.error(e))
    }
}