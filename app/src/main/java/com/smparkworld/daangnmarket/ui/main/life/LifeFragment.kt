package com.smparkworld.daangnmarket.ui.main.life

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.FragmentLifeBinding
import com.smparkworld.daangnmarket.ui.main.addLife.AddLifeActivity
import com.smparkworld.daangnmarket.ui.main.categoryList.CategoryListActivity


class LifeFragment : Fragment(), View.OnClickListener {

    private lateinit var lifeBinding: FragmentLifeBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_life, container, false)
        lifeBinding.life = this@LifeFragment
        setChipGroup()
        lifeBinding.refreshLayout.setOnRefreshListener {
            lifeBinding.chipGroup.removeAllViews()
            setChipGroup()
            lifeBinding.refreshLayout.isRefreshing = false
        }

        return lifeBinding.root

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_moveAddLife -> {
                val tempData: String? = DaangnApp.prefs.getData("id","N")
                if(tempData.equals("Y")){
                    Log.d("test!!", "임시저장데이터있음")
                }else
                    Log.d("test!!", "임시저장데이터없음")
                startActivity(Intent(activity, AddLifeActivity::class.java))
            }
            R.id.chip_categoryList ->{
                startActivity(Intent(activity, CategoryListActivity::class.java))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setChipGroup() {
        val totalValue: MutableMap<String, *>? = DaangnApp.prefs.getAll()
        val totalData: MutableMap<String, *>? = DaangnApp.prefs.getAllData()
        Log.d("test!!"," data : ${totalData.toString()}")

        totalValue?.forEach { (name, value) ->
            if (value as Boolean) {
                val chip = Chip(context)
                chip.text = name
                lifeBinding.chipGroup.addView(chip)
            }
        }
    }
}

