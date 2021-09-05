package com.smparkworld.daangnmarket.ui.main.life

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.ActivityAddLifeBinding
import com.smparkworld.daangnmarket.databinding.ActivityDetailLifeBinding
import com.smparkworld.daangnmarket.model.LifeList

class DetailLifeActivity : AppCompatActivity() {
    private lateinit var detailLifeBinding: ActivityDetailLifeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.getSerializableExtra("detailData") as LifeList

        detailLifeBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_life)
        detailLifeBinding.detatilData = data
        detailLifeBinding.detailLife = this@DetailLifeActivity

    }
}