package com.smparkworld.daangnmarket.data.remote.api

import com.google.gson.annotations.SerializedName
import com.smparkworld.daangnmarket.model.Address

/**
 * "/address/around" API에 대한 Response
 */
data class AddressAroundResponse(

    @SerializedName("latitude")
    var latitude: Double,

    @SerializedName("longitude")
    var longitude: Double,

    @SerializedName("nextPage")
    var nextPage: Int,

    @SerializedName("pageSize")
    var pageSize: Int,

    @SerializedName("count")
    var count: Int,

    @SerializedName("data")
    var data: List<Address>
)


/**
 * "/address/search" API에 대한 Response
 */
data class AddressSearchResponse(

    @SerializedName("search")
    var search: String,

    @SerializedName("nextPage")
    var nextPage: Int,

    @SerializedName("pageSize")
    var pageSize: Int,

    @SerializedName("count")
    var count: Int,

    @SerializedName("data")
    var data: List<Address>
)
