package com.smparkworld.daangnmarket.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.ui.launch.IntroActivity
import com.smparkworld.daangnmarket.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, IntroActivity::class.java))
        finish()
    }
}