package com.smparkworld.daangnmarket.data.local

interface UserLocalDataSource {

    suspend fun saveAccessToken(token: String, expiredIn: String)

    suspend fun saveRefreshToken(token: String, expiredIn: String)

    suspend fun getRefreshToken(): String?
}