package com.ashish.pixel.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "images")
@Parcelize
data class PixelImage(
@PrimaryKey val id: Int,
val photographer: String,
val photographer_url: String,
val url: String
):Parcelable

typealias PixelImages = List<PixelImage>