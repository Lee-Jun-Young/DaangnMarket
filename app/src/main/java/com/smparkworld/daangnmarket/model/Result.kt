package com.smparkworld.daangnmarket.model

import java.lang.Exception

sealed class Result<out R> {

    data class Success<T>(val data: T): Result<T>()
    data class Error(val e: Exception): Result<Nothing>() {
        fun printStackTrace() { e.printStackTrace() }
    }

    override fun toString(): String {
        return when(this) {
            is Success -> "Success[data:: $data]"
            is Error -> "Error[data:: $e]"
        }
    }
}