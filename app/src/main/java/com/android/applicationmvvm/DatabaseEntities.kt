package com.android.applicationmvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabasePets(
    @PrimaryKey
    val url: String,
    val name: String,
    val age: Double,
    val type: String,
    val color: String
)

fun List<DatabasePets>.asDomainModel(): List<DomainPets> {
    return map {
        DomainPets(
            url = it.url,
            name = it.name,
            age = it.age,
            type = it.type,
            color = it.color
        )
    }
}