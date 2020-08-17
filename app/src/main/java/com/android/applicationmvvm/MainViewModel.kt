package com.android.applicationmvvm

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

class MainViewModel(private val app: Application) : AndroidViewModel(app) {

    fun getAllPets() {
        Toast.makeText(app, "All Pets", Toast.LENGTH_SHORT).show()
    }

    fun getDogs() {
        Toast.makeText(app, "Dogs", Toast.LENGTH_SHORT).show()
    }

    fun getCats() {
        Toast.makeText(app, "Cats", Toast.LENGTH_SHORT).show()
    }

}