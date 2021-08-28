package com.smparkworld.daangnmarket.data.repository

import com.smparkworld.daangnmarket.model.Result

interface UserRepository {

    suspend fun login(phone: String, address: Int): Result<Boolean>

    suspend fun loginWithToken(): Result<Boolean>
}