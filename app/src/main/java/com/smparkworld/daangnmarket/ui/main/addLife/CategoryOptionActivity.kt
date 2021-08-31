package com.smparkworld.daangnmarket.ui.main.addLife

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.ActivityCategoryOptionBinding

class CategoryOptionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var categoryOptionBinding: ActivityCategoryOptionBinding
    private val categoryOptionViewModel: CategoryOptionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryOptionBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_category_option)
        categoryOptionBinding.categoryOption = this@CategoryOptionActivity

        categoryOptionBinding.lifecycleOwner = this
        categoryOptionBinding.viewModel = categoryOptionViewModel

        setRecyclerview()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun setRecyclerview() {
        val data = categoryOptionViewModel.getAll()

        if (data.isNotEmpty())
            categoryOptionBinding.tvSecondSubTitle.isVisible = true
        else
            categoryOptionBinding.tvSecondSubTitle.isGone = true

        val adapter = CategoryOptionAdapter(this, data)
        categoryOptionBinding.categoryOptionRecyclerview.layoutManager = LinearLayoutManager(this)
        categoryOptionBinding.categoryOptionRecyclerview.adapter = adapter
        categoryOptionBinding.categoryOptionRecyclerview.setHasFixedSize(true)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.tv_categoryOptionOne -> {
                sendResult(this.getString(R.string.activityCategoryOption_firstSubTitle_firstText))
                finish()
            }
            R.id.tv_categoryOptionTwo -> {
                sendResult(this.getString(R.string.activityCategoryOption_firstSubTitle_secondText))
                finish()
            }

        }
    }

    private fun sendResult(string: String) {
        val intent = Intent(this, AddLifeActivity::class.java).apply {
            putExtra("result", string)
            addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        }
        setResult(RESULT_OK, intent)
    }

}