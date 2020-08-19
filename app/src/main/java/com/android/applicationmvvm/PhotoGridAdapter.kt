package com.android.applicationmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.applicationmvvm.databinding.GridItemBinding

class PhotoGridAdapter :
    ListAdapter<PetDetails, PhotoGridAdapter.PetDetailsViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.PetDetailsViewHolder {
        return PetDetailsViewHolder((GridItemBinding.inflate(LayoutInflater.from(parent.context))))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.PetDetailsViewHolder, position: Int) {
        val pet = getItem(position)
        holder.bind(pet)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<PetDetails>() {
        override fun areItemsTheSame(oldItem: PetDetails, newItem: PetDetails): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PetDetails, newItem: PetDetails): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class PetDetailsViewHolder(private var binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(petDetails: PetDetails) {
            binding.petDetail = petDetails
            binding.executePendingBindings()
        }
    }
}