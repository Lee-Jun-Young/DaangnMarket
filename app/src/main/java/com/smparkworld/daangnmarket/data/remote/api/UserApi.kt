package com.smparkworld.daangnmarket.data.remote.api

import com.smparkworld.daangnmarket.di.NetworkModule
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @FormUrlEncoded
    @POST("/user/sign")
    suspend fun sign(
            @Field("phone") phone: String,
            @Field("address") address: Int
    ): Response<UserSignResponse>

    @POST("/user/refresh")
    suspend fun refreshToken(
            @Header(NetworkModule.TOKEN_KEY) refreshToken: String
    ): Response<UserRefreshResponse>
}