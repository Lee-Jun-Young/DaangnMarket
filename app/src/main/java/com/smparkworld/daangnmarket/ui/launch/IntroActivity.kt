package com.smparkworld.daangnmarket.ui.launch

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    private val rippleAnim by lazy {
        AnimationUtils.loadAnimation(this, R.anim.set_launch_markerripple)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityIntroBinding>(
            this, R.layout.activity_intro
        ).apply {
            btnStart.setOnClickListener {
                startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.ivRipple.startAnimation(rippleAnim)
    }

    override fun onStop() {
        super.onStop()
        rippleAnim.cancel()
    }
}