package com.smparkworld.daangnmarket.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.ActivityMainBinding
import com.smparkworld.daangnmarket.extension.setupWithNavController
import com.smparkworld.daangnmarket.ui.main.chat.ChatFragment
import com.smparkworld.daangnmarket.ui.main.home.HomeFragment
import com.smparkworld.daangnmarket.ui.main.life.LifeFragment
import com.smparkworld.daangnmarket.ui.main.myinfo.MyInfoFragment
import com.smparkworld.daangnmarket.ui.main.mylocation.MyLocationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {

            btmNaviView.setupWithNavController(supportFragmentManager, R.id.fragContainer, listOf(
                    HomeFragment(), LifeFragment(), MyLocationFragment(), ChatFragment(), MyInfoFragment()
            ))
        }
    }
}