package com.smparkworld.daangnmarket

import android.app.Application
import com.smparkworld.daangnmarket.di.DaggerAppComponent

class DaangnApp : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        const val REQUEST_PERMISSION_CODE = 1000
    }
}