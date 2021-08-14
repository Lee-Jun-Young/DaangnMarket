package com.smparkworld.daangnmarket.ui.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.ui.launch.di.LoginComponent

class LoginActivity : AppCompatActivity() {

    lateinit var loginComponent: LoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        loginComponent = (application as DaangnApp).appComponent.loginComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_login)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddressFragment())
                .commitNow()
    }
}