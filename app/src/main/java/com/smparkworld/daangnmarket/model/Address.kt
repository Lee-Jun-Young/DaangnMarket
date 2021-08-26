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
)

sealed class AddressModel {

    class Header(val text: String): AddressModel()
    class Item(val item: Address): AddressModel()
    object Separator: AddressModel()

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<AddressModel>() {
            override fun areItemsTheSame(oldItem: AddressModel, newItem: AddressModel) =
                if (oldItem is Header && newItem is Header) {
                    oldItem == newItem
                } else if (oldItem is Item && newItem is Item) {
                    oldItem.item.id == newItem.item.id
                } else false

            override fun areContentsTheSame(oldItem: AddressModel, newItem: AddressModel) =
                if (oldItem is Header && newItem is Header) {
                    oldItem.text == newItem.text
                } else if (oldItem is Item && newItem is Item){
                    oldItem.item.address == newItem.item.address
                            && oldItem.item.latitude == newItem.item.latitude
                            && oldItem.item.longitude == newItem.item.longitude
                } else false

        }
    }
}