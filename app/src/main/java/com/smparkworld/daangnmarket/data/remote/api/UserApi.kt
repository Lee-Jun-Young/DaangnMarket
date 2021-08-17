package com.smparkworld.daangnmarket.data.remote.api

import com.smparkworld.daangnmarket.di.NetworkModule
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {

    @POST("/user/refresh")
    suspend fun refreshToken(
            @Header(NetworkModule.TOKEN_KEY) refreshToken: String
    ): Response<UserRefreshResponse>
}