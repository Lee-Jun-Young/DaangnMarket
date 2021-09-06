package com.smparkworld.daangnmarket.di

import android.content.SharedPreferences
import com.smparkworld.daangnmarket.BuildConfig
import com.smparkworld.daangnmarket.data.remote.api.AddressApi
import com.smparkworld.daangnmarket.data.remote.api.UserApi
import com.smparkworld.daangnmarket.utils.TokenAuthenticator
import dagger.Module
import dagger.Provides
import com.smparkworld.daangnmarket.di.AppModule.AppPref
import com.smparkworld.daangnmarket.utils.PreferencesKey
import com.smparkworld.daangnmarket.utils.RsaCipherHelper
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    private fun getClient(
        pref: SharedPreferences,
        authenticator: Authenticator
    ): OkHttpClient {
        val token = pref.getString(PreferencesKey.USER_ACCESS_TOKEN, null)?.run {
            RsaCipherHelper.decrypt(this)
        }
        val interceptor = Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().addHeader(BuildConfig.TOKEN_KEY, token).build())
        }
        return OkHttpClient().newBuilder().run {
            if (token != null) addInterceptor(interceptor)
            authenticator(authenticator)
            build()
        }
    }


    @Provides
    @JvmStatic
    fun provideRetrofit(
        @AppPref pref: SharedPreferences,
        authenticator: TokenAuthenticator
    ): Retrofit =
            Retrofit.Builder().client(getClient(pref, authenticator))
                    .baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Provides
    @JvmStatic
    fun provideAddressApi(retrofit: Retrofit): AddressApi =
            retrofit.create(AddressApi::class.java)

    @Provides
    @JvmStatic
    fun provideUserApi(retrofit: Retrofit): UserApi =
            retrofit.create(UserApi::class.java)
}