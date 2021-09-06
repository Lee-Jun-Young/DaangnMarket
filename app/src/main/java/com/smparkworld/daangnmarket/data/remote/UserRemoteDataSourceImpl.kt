package com.smparkworld.daangnmarket.data.remote

import com.smparkworld.daangnmarket.data.remote.api.UserApi
import com.smparkworld.daangnmarket.data.remote.api.UserLoginWithTokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
        private val userApi: UserApi
) : UserRemoteDataSource {

    override suspend fun login(
            phone: String,
            address: Int
    ) = withContext(Dispatchers.IO) {
        userApi.login(phone, address)
    }

    override suspend fun loginWithToken() =
            withContext(Dispatchers.IO) { userApi.loginWithToken() }

    override suspend fun refresh(
            refreshToken: String
    ) = withContext(Dispatchers.IO) {
        userApi.refreshToken(refreshToken)
    }
}