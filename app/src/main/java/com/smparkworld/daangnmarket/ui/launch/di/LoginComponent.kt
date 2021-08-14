package com.smparkworld.daangnmarket.ui.launch.di

import com.smparkworld.daangnmarket.ui.launch.AddressFragment
import com.smparkworld.daangnmarket.ui.launch.LoginActivity
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(addressFragment: AddressFragment)
}