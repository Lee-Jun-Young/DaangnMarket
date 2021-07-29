package com.smparkworld.daangnmarket.di

import android.content.Context
import com.smparkworld.daangnmarket.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AppBinds::class,
    NetworkModule::class,
    ViewModelModule::class,
    Subcomponents::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}

@Module(subcomponents = [
])
object Subcomponents