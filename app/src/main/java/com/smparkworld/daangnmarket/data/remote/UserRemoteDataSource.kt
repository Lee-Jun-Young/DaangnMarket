package com.smparkworld.daangnmarket.data.remote

import com.smparkworld.daangnmarket.data.remote.api.UserRefreshResponse
import com.smparkworld.daangnmarket.data.remote.api.UserSignResponse
import retrofit2.Response

interface UserRemoteDataSource {

    suspend fun signUp(phone: String, address: Int): Response<UserSignResponse>

    suspend fun refresh(refreshToken: String): Response<UserRefreshResponse>
}