package com.smparkworld.daangnmarket.data.remote.api

import com.google.gson.annotations.SerializedName

data class UserRefreshResponse(

    @SerializedName("accessToken")
    var accessToken: String,

    @SerializedName("expiredIn")
    var expiredIn: String
)