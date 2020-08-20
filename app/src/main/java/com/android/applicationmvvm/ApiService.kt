package com.android.applicationmvvm

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

enum class MyApiFilter(val value: String) {
    SHOW_ALL(""),
    SHOW_CATS("cat"),
    SHOW_DOGS("dog")
}

private const val BASE_URL = "https://mvvm-application.glitch.me/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MyApiService {
    @GET("api/see-pet-list")
    suspend fun getProperties(@Query("type") type: String): List<PetDetails>
}

object MyApi {
    val retrofitService: MyApiService by lazy {
        retrofit.create(MyApiService::class.java)
    }
}