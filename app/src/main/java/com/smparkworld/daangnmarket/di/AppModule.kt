package com.smparkworld.daangnmarket.di

import android.content.Context
import com.smparkworld.daangnmarket.BuildConfig
import com.smparkworld.daangnmarket.R
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class AppPref

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class UserPref

    @Provides
    @JvmStatic
    @Singleton
    @AppPref
    fun provideAppPref(applicationContext: Context) = applicationContext.getSharedPreferences(
                BuildConfig.PREF_NAME_APP, Context.MODE_PRIVATE
    )

    @Provides
    @JvmStatic
    @Singleton
    @UserPref
    fun provideUserPref(applicationContext: Context) = applicationContext.getSharedPreferences(
                BuildConfig.PREF_NAME_APP, Context.MODE_PRIVATE
    )

}