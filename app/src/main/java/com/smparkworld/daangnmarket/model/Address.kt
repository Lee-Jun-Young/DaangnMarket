package com.smparkworld.daangnmarket.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Address(

    @SerializedName("id")
    var id: Int,

    @SerializedName("address")
    var address: String,

    @SerializedName("latitude")
    var latitude: Double,

    @SerializedName("longitude")
    var longitude: Double
) {
    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Address>() {
            override fun areItemsTheSame(oldItem: Address, newItem: Address) =
                    oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Address, newItem: Address) =
                    oldItem.address == newItem.address && oldItem.latitude == newItem.latitude
                            && oldItem.longitude == newItem.longitude
        }
    }
}
