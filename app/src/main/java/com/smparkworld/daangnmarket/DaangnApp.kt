package com.smparkworld.daangnmarket

import android.app.Application
import com.smparkworld.daangnmarket.di.DaggerAppComponent
import com.smparkworld.daangnmarket.extension.PreferenceUtil

class DaangnApp : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }

    companion object{
        lateinit var prefs: PreferenceUtil
    }
}