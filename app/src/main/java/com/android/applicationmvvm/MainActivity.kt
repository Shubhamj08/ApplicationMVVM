package com.android.applicationmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.android.applicationmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.photosGrid.adapter = PhotoGridAdapter()

        binding.searchButton.setOnClickListener {
            binding.apply {
                when {
                    allPets.isChecked -> viewModel.getPets(MyApiFilter.SHOW_ALL.value)
                    dogs.isChecked && cats.isChecked -> viewModel.getPets(MyApiFilter.SHOW_ALL.value)
                    dogs.isChecked -> viewModel.getPets(MyApiFilter.SHOW_DOGS.value)
                    cats.isChecked -> viewModel.getPets(MyApiFilter.SHOW_CATS.value)
                }
                bindRecyclerView(photosGrid, viewModel.pets)
            }
        }

        binding.lifecycleOwner = this
    }
}

