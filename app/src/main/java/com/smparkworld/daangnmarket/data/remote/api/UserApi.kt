package com.smparkworld.daangnmarket.data.remote.api

import com.smparkworld.daangnmarket.BuildConfig
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
            @Field("phone") phone: String,
            @Field("address") address: Int
    ): Response<UserLoginResponse>

    @POST("/user/loginWithToken")
    suspend fun loginWithToken(): Response<UserLoginWithTokenResponse>

    @POST("/user/refresh")
    suspend fun refreshToken(
            @Header(BuildConfig.TOKEN_KEY) refreshToken: String
    ): Response<UserRefreshResponse>
}