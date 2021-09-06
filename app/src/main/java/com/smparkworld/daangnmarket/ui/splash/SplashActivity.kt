package com.smparkworld.daangnmarket.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.ui.launch.IntroActivity
import com.smparkworld.daangnmarket.ui.main.MainActivity
import com.smparkworld.daangnmarket.ui.splash.di.SplashComponent
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    lateinit var splashComponent: SplashComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val splashViewModel by viewModels<SplashViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        splashComponent = (application as DaangnApp).appComponent.splashComponent().create()
        splashComponent.inject(this)

        super.onCreate(savedInstanceState)
        initObserver()

        splashViewModel.loginWithToken()
    }

    private fun initObserver() {
        splashViewModel.login.observe(this) {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            }
        }
    }
}