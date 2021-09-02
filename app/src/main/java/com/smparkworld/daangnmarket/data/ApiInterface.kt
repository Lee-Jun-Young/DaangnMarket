package com.smparkworld.daangnmarket.data

import com.smparkworld.daangnmarket.model.LifeList
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("life_getList.php")
    fun getLifeData() : Call<List<LifeList>>

}