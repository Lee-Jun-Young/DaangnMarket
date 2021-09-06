package com.smparkworld.daangnmarket.ui.splash.di

import com.smparkworld.daangnmarket.ui.splash.SplashActivity
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashComponent
    }

    fun inject(splashActivity: SplashActivity)
}