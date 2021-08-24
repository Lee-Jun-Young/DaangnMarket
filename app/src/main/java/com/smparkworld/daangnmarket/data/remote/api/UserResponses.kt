package com.smparkworld.daangnmarket.data.remote.api

import com.google.gson.annotations.SerializedName

data class UserRefreshResponse(

    @SerializedName("accessToken")
    var accessToken: String,

    @SerializedName("expiredIn")
    var expiredIn: String
)

data class UserSignResponse(

    @SerializedName("accessToken")
    var accessToken: String,

    @SerializedName("expiredIn")
    var expiredIn: String,

    @SerializedName("refreshToken")
    var refreshToken: String,

    @SerializedName("refreshToken_expiredIn")
    var refreshTokenExpiredIn: String,
)