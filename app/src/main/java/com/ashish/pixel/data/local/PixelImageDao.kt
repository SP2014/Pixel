package com.ashish.pixel.data.local

import androidx.room.*
import com.ashish.pixel.data.models.PixelImage
import com.ashish.pixel.data.models.PixelImages
import kotlinx.coroutines.flow.Flow

@Dao
interface PixelImageDao {

    @Query("SELECT * FROM images")
    fun getAllImages(): Flow<PixelImages>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(vararg image: PixelImage)

    @Delete
    suspend fun deleteImage(vararg images: PixelImage)

}