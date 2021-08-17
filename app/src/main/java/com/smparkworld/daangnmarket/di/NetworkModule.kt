package com.smparkworld.daangnmarket.di

import android.content.SharedPreferences
import com.smparkworld.daangnmarket.data.remote.api.AddressApi
import com.smparkworld.daangnmarket.di.AppModule.AppPref
import com.smparkworld.daangnmarket.utils.PreferencesKey.USER_ACCESS_TOKEN
import com.smparkworld.daangnmarket.utils.RsaCipherHelper
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    private const val BASE_URL = "http://smparkworld.com:3001/"
    const val TOKEN_KEY = "X-AUTH-TOKEN"

    private fun getClient(token: String?) : OkHttpClient {
        val interceptor = Interceptor { chain ->
            val url = chain.request().url().newBuilder().addQueryParameter(TOKEN_KEY, token).build()
            val req = chain.request().newBuilder().url(url).build()
            chain.proceed(req)
        }
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    private fun getRetrofit(pref: SharedPreferences): Retrofit {
        val accessToken = pref.getString(USER_ACCESS_TOKEN, null)?.let {
            RsaCipherHelper.decrypt(it)
        }
        return Retrofit.Builder().client(getClient(accessToken))
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun provideAddressApi(@AppPref pref: SharedPreferences): AddressApi =
            getRetrofit(pref).create(AddressApi::class.java)
}