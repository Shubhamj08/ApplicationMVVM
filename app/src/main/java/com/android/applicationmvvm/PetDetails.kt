package com.android.applicationmvvm

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class PetDetails(
    val name: String,
    val type: String,
    val age: Double,
    val color: String,
    @Json(name = "img_src") val imgUrl: String
)

fun List<PetDetails>.asDomainModel(): List<DomainPets> {
    return map {
        DomainPets(
            url = it.imgUrl,
            name = it.name,
            type = it.type,
            age = it.age,
            color = it.color
        )
    }
}

fun List<PetDetails>.asDatabaseModel(): List<DatabasePets> {
    return map {
        DatabasePets(
            url = it.imgUrl,
            name = it.name,
            type = it.type,
            age = it.age,
            color = it.color
        )
    }
}