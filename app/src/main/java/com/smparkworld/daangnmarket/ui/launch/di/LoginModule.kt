package com.smparkworld.daangnmarket.ui.launch.di

import androidx.lifecycle.ViewModel
import com.smparkworld.daangnmarket.di.ViewModelKey
import com.smparkworld.daangnmarket.ui.launch.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}