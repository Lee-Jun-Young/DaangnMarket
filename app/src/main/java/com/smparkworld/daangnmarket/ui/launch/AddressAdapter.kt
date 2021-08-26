package com.smparkworld.daangnmarket.ui.launch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.ItemLaunchAddressBinding
import com.smparkworld.daangnmarket.databinding.ItemLaunchAddressHeaderBinding
import com.smparkworld.daangnmarket.model.Address
import com.smparkworld.daangnmarket.model.AddressModel

class AddressAdapter(
        private val onClick: (Address) -> Unit
) : PagingDataAdapter<AddressModel, RecyclerView.ViewHolder>(AddressModel.DIFF_CALLBACK) {

    override fun getItemViewType(position: Int) =
        when (getItem((position))) {
            is AddressModel.Header  -> R.layout.item_launch_address_header
            is AddressModel.Item    -> R.layout.item_launch_address
            else -> R.layout.item_launch_address_separator
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            R.layout.item_launch_address_header -> {
                AddressHeaderViewHolder(
                    ItemLaunchAddressHeaderBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            R.layout.item_launch_address -> {
                AddressItemViewHolder(
                    ItemLaunchAddressBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), onClick
                )
            }
            else -> {
                AddressSeparatorViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_launch_address_separator, parent, false
                    )
                )
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is AddressHeaderViewHolder -> {
                holder.bind(item as AddressModel.Header)
            }
            is AddressItemViewHolder -> {
                holder.bind(item as AddressModel.Item)
            }
        }
    }

    class AddressHeaderViewHolder(
        private val binding: ItemLaunchAddressHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: AddressModel.Header) {
            binding.text = model.text
            binding.executePendingBindings()
        }
    }

    class AddressItemViewHolder(
            private val binding: ItemLaunchAddressBinding,
            private val onClick: (Address) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: AddressModel.Item) {
            binding.address = model.item
            binding.onClick = onClick
            binding.executePendingBindings()
        }
    }

    class AddressSeparatorViewHolder(view: View) : RecyclerView.ViewHolder(view)
}