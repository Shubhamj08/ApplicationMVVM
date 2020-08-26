package com.android.applicationmvvm

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<DomainPets>?
) {
    data?.let {
        val adapter = recyclerView.adapter as PhotoGridAdapter
        Log.i("fetch", "We Reached Here: $data")
        adapter.submitList(data)
    }
}

@BindingAdapter("textFormat")
fun formatText(view: TextView, pets: DomainPets?) {
    pets?.let {
        val age = "${pets.age.toInt()} Years"
        view.text = age
    }
}