package com.smparkworld.daangnmarket.ui.launch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.daangnmarket.databinding.ItemLaunchAddressBinding
import com.smparkworld.daangnmarket.model.Address

class AddressAdapter(
        private val onClick: (Address) -> Unit
) : PagingDataAdapter<Address, AddressAdapter.AddressViewHolder>(Address.DIFF_CALLBACK) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AddressViewHolder(
            ItemLaunchAddressBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
            ), onClick
    )

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class AddressViewHolder(
            private val binding: ItemLaunchAddressBinding,
            private val onClick: (Address) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(address: Address) {
            binding.address = address
            binding.onClick = onClick
            binding.executePendingBindings()
        }
    }
}