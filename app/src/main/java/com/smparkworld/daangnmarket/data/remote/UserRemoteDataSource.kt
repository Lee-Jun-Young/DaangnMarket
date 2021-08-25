package com.smparkworld.daangnmarket.data.remote

import com.smparkworld.daangnmarket.data.remote.api.UserLoginResponse
import com.smparkworld.daangnmarket.data.remote.api.UserRefreshResponse
import retrofit2.Response

interface UserRemoteDataSource {

    suspend fun signUp(phone: String, address: Int): Response<UserLoginResponse>

    suspend fun refresh(refreshToken: String): Response<UserRefreshResponse>
}