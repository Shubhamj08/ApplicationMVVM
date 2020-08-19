package com.android.applicationmvvm

import com.squareup.moshi.Json

data class PetDetails(
    val name: String,
    val type: String,
    val age: Double,
    val color: String,
    @Json(name = "img_src") val imgUrl: String
)