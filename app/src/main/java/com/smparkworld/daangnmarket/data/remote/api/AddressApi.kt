package com.smparkworld.daangnmarket.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressApi {

    @GET("/address/around")
    suspend fun getAround(
            @Query("latitude") latitude: Double,
            @Query("longitude") longitude: Double,
            @Query("page") page: Int,
            @Query("size") size: Int
    ): Response<AddressAroundResponse>
}