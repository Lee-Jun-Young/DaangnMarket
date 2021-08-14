package com.smparkworld.daangnmarket.di

import android.content.Context
import com.smparkworld.daangnmarket.ui.launch.di.LoginComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AppBinds::class,
    NetworkModule::class,
    ViewModelModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun loginComponent(): LoginComponent.Factory
}