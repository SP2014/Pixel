package com.ashish.pixel.data.models

import com.google.gson.annotations.SerializedName

data class PexelResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("photos") val photos: List<Photos>,
    @SerializedName("total_results") val totalResult: Int,
    @SerializedName("next_page") val nextPage: String
)

data class Photos(
    @SerializedName("id") val id: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("photographer") val photographer: String,
    @SerializedName("photographer_url") val photographerUrl: String,
    @SerializedName( "photographer_id") val photographerId: Int,
    @SerializedName("avg_color") val avgColor: String,
    @SerializedName("src") val src: Src,
    @SerializedName("liked") val liked: Boolean
)

data class Src(
    @SerializedName("original") val original: String,
    @SerializedName("large2x") val extraLarge: String,
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("small") val small: String,
    @SerializedName("portrait") val potrait: String,
    @SerializedName("landscape") val landscape: String,
    @SerializedName("tiny") val tiny: String

)

