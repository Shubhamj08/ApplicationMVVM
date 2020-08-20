package com.android.applicationmvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PetsRepository(private val database: PetDatabase) {

    val pets: LiveData<List<DomainPets>> = Transformations.map(database.petDao.getPets()) {
        it.asDomainModel()
    }

    suspend fun refreshContents() {
        withContext(Dispatchers.IO) {
            try {
                val allPets = MyApi.retrofitService.getProperties(MyApiFilter.SHOW_ALL.value)
                database.petDao.insertAll(allPets.asDatabaseModel())
            } catch (e: Exception) {
                Log.i("fetch", "failure : ${e.message}")
            }
        }
    }
}