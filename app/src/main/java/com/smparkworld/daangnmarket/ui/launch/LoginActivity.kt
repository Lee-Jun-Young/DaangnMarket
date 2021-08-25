package com.smparkworld.daangnmarket.ui.launch

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.extension.showSnackbar
import com.smparkworld.daangnmarket.ui.launch.di.LoginComponent
import com.smparkworld.daangnmarket.ui.main.MainActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    lateinit var loginComponent: LoginComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val loginViewModel by viewModels<LoginViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        loginComponent = (application as DaangnApp).appComponent.loginComponent().create()
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_login)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddressFragment())
                .commitNow()

        initObserver()
    }

    private fun initObserver() {
        loginViewModel.error.observe(this) {
            showSnackbar(it)
        }
        loginViewModel.selectedAddress.observe(this) {
            nextStep()
        }
        loginViewModel.login.observe(this) {
            Intent(this, MainActivity::class.java).let {
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(it)
            }
        }
    }

    private fun nextStep() {
        android.R.anim.slide_in_left
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.anim.translate_launch_swipe_next_enter,
                        R.anim.translate_launch_swipe_next_exit,
                        R.anim.translate_launch_swipe_back_enter,
                        R.anim.translate_launch_swipe_back_exit,
                )
                .replace(R.id.fragmentContainer, AuthFragment())
                .addToBackStack(null)
                .commit()
    }
}