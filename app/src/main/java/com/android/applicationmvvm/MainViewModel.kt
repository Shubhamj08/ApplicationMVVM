package com.android.applicationmvvm

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val app: Application) : AndroidViewModel(app) {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    fun getAllPets() {
        getPetList()
    }

    fun getDogs() {
        Toast.makeText(app, "Dogs", Toast.LENGTH_SHORT).show()
    }

    fun getCats() {
        Toast.makeText(app, "Cats", Toast.LENGTH_SHORT).show()
    }

    private fun getPetList() {
        Log.i("fetch", "fetching")
        MyApi.retrofitService.getProperties().enqueue(
            object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure" + t.message
                    Log.i("fetch", "not fetched")
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                    Log.i("fetch", "${_response.value}")
                }
            }
        )
    }
}