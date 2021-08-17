package com.smparkworld.daangnmarket.ui.launch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.daangnmarket.databinding.ItemLaunchAddressBinding
import com.smparkworld.daangnmarket.model.Address

class AddressAdapter : PagingDataAdapter<Address, AddressAdapter.AddressViewHolder>(Address.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AddressViewHolder(
            ItemLaunchAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class AddressViewHolder(
            private val binding: ItemLaunchAddressBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(address: Address) {
            binding.address = address.address
            binding.executePendingBindings()
        }
    }
}