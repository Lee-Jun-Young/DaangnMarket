package com.smparkworld.daangnmarket.data.remote

import com.smparkworld.daangnmarket.data.remote.api.UserLoginResponse
import com.smparkworld.daangnmarket.data.remote.api.UserLoginWithTokenResponse
import com.smparkworld.daangnmarket.data.remote.api.UserRefreshResponse
import retrofit2.Response

interface UserRemoteDataSource {

    suspend fun login(phone: String, address: Int): Response<UserLoginResponse>

    suspend fun loginWithToken(): Response<UserLoginWithTokenResponse>

    suspend fun refresh(refreshToken: String): Response<UserRefreshResponse>
}