package com.smparkworld.daangnmarket.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://leejy.dothome.co.kr/leejy/"
    private val gson: Gson = GsonBuilder().setLenient().create()

    fun getInstance(): ApiInterface {
        val retrofit : ApiInterface? = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiInterface::class.java)

        return retrofit!!

    }
}


