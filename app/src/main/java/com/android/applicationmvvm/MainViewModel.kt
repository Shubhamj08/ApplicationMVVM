package com.android.applicationmvvm

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

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
        viewModelScope.launch {
            try {
                val resultList = MyApi.retrofitService.getProperties()
                _response.value = "${resultList.size}"
                Log.i("fetch", "success")
            } catch (e: Exception) {
                _response.value = "Failed: ${e.message}"
                Log.i("fetch", "failure")
            }
        }
    }
}