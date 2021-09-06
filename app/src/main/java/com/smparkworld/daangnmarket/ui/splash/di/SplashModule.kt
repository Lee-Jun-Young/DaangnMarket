package com.smparkworld.daangnmarket.ui.splash.di

import androidx.lifecycle.ViewModel
import com.smparkworld.daangnmarket.di.ViewModelKey
import com.smparkworld.daangnmarket.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}