package com.android.applicationmvvm

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class MainViewModel(private val app: Application) : AndroidViewModel(app) {

    lateinit var pet: LiveData<List<DomainPets>>

    private val repository = PetsRepository(getDataBase((app)))

    var pets: List<DomainPets>? = listOf()

    init {
        refreshFromRepository()
        getPets(MyApiFilter.SHOW_ALL.value)
    }

    fun getPets(filter: String) {
        pet = repository.pets
        when (filter) {
            "" -> {
                pets = pet.value?.filter {
                    it.name.isNotEmpty()
                }
                return
            }
            "dog" -> {
                pets = pet.value?.filter { list ->
                    list.type == MyApiFilter.SHOW_DOGS.value
                }
                Log.i("fetch", "dogs: ${pets?.size}")
                return
            }
            "cat" -> {
                pets = pet.value?.filter { list ->
                    list.type == MyApiFilter.SHOW_CATS.value
                }
                Log.i("fetch", "cats: ${pets?.size}")
                return
            }
        }
    }

    private fun refreshFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshContents()
            } catch (e: Exception) {
                Log.i("fetch", "error in vm: $e")
            }
        }
    }
}