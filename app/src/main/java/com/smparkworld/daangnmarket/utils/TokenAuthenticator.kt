package com.smparkworld.daangnmarket.utils

import com.smparkworld.daangnmarket.BuildConfig
import com.smparkworld.daangnmarket.data.local.UserLocalDataSource
import com.smparkworld.daangnmarket.data.remote.api.UserApi
import kotlinx.coroutines.*
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
        private val userLocalDataSource: UserLocalDataSource
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        if (response.code() == 401) {

            val token = runBlocking { getNewAccessToken() }
            if (token != null) {
                return response.request()
                        .newBuilder()
                        .removeHeader(BuildConfig.TOKEN_KEY)
                        .addHeader(BuildConfig.TOKEN_KEY, token)
                        .build()
            }
        }
        return null
    }

    private fun getApi() = Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)

    private suspend fun getNewAccessToken(): String? {

        val refreshToken = userLocalDataSource.getRefreshToken()
        if (refreshToken != null) {

            val res = getApi().refreshToken(refreshToken)
            val body = res.body()
            if (res.isSuccessful && body != null) {
                userLocalDataSource.saveAccessToken(body.accessToken, body.expiredIn)
                return body.accessToken
            }
        }
        return null
    }
}