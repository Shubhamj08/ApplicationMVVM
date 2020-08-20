package com.android.applicationmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.applicationmvvm.databinding.GridItemBinding

class PhotoGridAdapter :
    ListAdapter<DomainPets, PhotoGridAdapter.PetDetailsViewHolder>(DiffCallBack) {
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

    companion object DiffCallBack : DiffUtil.ItemCallback<DomainPets>() {
        override fun areItemsTheSame(oldItem: DomainPets, newItem: DomainPets): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DomainPets, newItem: DomainPets): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class PetDetailsViewHolder(private var binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(domainPets: DomainPets) {
            binding.domainPet = domainPets
            binding.executePendingBindings()
        }
    }
}