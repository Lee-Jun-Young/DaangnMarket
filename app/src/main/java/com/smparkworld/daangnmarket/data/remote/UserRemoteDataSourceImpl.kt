package com.smparkworld.daangnmarket.data.remote

import com.smparkworld.daangnmarket.data.remote.api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
        private val userApi: UserApi
) : UserRemoteDataSource {

    override suspend fun signUp(
            phone: String,
            address: Int
    ) = withContext(Dispatchers.IO) {
        userApi.signUp(phone, address)
    }

    override suspend fun refresh(
            refreshToken: String
    ) = withContext(Dispatchers.IO) {
        userApi.refreshToken(refreshToken)
    }
}