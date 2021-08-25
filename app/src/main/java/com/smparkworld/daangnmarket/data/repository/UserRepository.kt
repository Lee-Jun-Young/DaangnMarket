package com.smparkworld.daangnmarket.data.repository

import com.smparkworld.daangnmarket.model.Result

interface UserRepository {

    suspend fun signUp(phone: String, address: Int): Result<Boolean>
}