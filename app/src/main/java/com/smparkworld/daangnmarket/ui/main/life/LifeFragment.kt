package com.smparkworld.daangnmarket.ui.main.life

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.data.RetrofitClient
import com.smparkworld.daangnmarket.databinding.FragmentLifeBinding
import com.smparkworld.daangnmarket.model.LifeList
import com.smparkworld.daangnmarket.ui.main.addLife.AddLifeActivity
import com.smparkworld.daangnmarket.ui.main.categoryList.CategoryListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LifeFragment : Fragment(), View.OnClickListener {

    private lateinit var lifeBinding: FragmentLifeBinding
    private val spinnerItems = arrayListOf<String>("상봉동", "면목본동", "내 동네 설정")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_life, container, false)
        lifeBinding.life = this@LifeFragment

        setChipGroup()
        initSpinner()
        loadData()
        setupSpinnerHandler()

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
                startActivity(Intent(activity, AddLifeActivity::class.java))
            }
            R.id.chip_categoryList -> {
                startActivity(Intent(activity, CategoryListActivity::class.java))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setChipGroup() {
        val totalValue: MutableMap<String, *>? = DaangnApp.prefs.getAll()

        totalValue?.forEach { (name, value) ->
            if (value as Boolean) {
                val chip = Chip(context)
                chip.text = name
                lifeBinding.chipGroup.addView(chip)
            }
        }
    }

    private fun initSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItems
        )
        lifeBinding.spinner.adapter = adapter
    }

    private fun setupSpinnerHandler() {
        lifeBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun loadData() {

        RetrofitClient.getInstance().getLifeData().enqueue(object : Callback<List<LifeList>> {
            override fun onResponse(call: Call<List<LifeList>>, response: Response<List<LifeList>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        Log.d("this is!!", body.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<LifeList>>, t: Throwable) {
                Log.d("this is error", t.message.toString())
            }
        })
    }

}

