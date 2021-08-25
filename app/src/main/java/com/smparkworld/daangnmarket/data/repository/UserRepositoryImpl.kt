package com.smparkworld.daangnmarket.data.repository

import com.smparkworld.daangnmarket.data.local.UserLocalDataSource
import com.smparkworld.daangnmarket.data.remote.UserRemoteDataSource
import com.smparkworld.daangnmarket.model.Result.Success
import com.smparkworld.daangnmarket.model.Result.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
        private val localDataSource: UserLocalDataSource,
        private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun login(
            phone: String,
            address: Int
    ) = withContext(Dispatchers.IO) {

        try {
            val response = remoteDataSource.login(phone, address)
            if (response.isSuccessful) {

                val body = response.body()
                if (body != null) {

                    return@withContext if (body.existing) {
                        localDataSource.saveAccessToken(body.accessToken, body.expiredIn)
                        localDataSource.saveRefreshToken(body.refreshToken, body.refreshTokenExpiredIn)
                        Success(true)
                    } else {
                        Success(false)
                    }
                } else {
                    throw NullPointerException("the response body is null.")
                }
            } else {
                throw HttpException(response)
            }
        } catch(e: Exception) {
            return@withContext Error(e)
        }
    }
}