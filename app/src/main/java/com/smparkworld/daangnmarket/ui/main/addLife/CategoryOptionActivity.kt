package com.smparkworld.daangnmarket.ui.main.addLife

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.ActivityCategoryOptionBinding

class CategoryOptionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var categoryOptionBinding: ActivityCategoryOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryOptionBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_option)
        categoryOptionBinding.categoryOption = this@CategoryOptionActivity
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back -> {
                finish()
            }
            R.id.tv_categoryOptionOne -> {
                sendResult(applicationContext.getString(R.string.activityCategoryOption_firstSubTitle_firstText))
                finish()
            }
            R.id.tv_categoryOptionTwo -> {
                sendResult(applicationContext.getString(R.string.activityCategoryOption_firstSubTitle_secondText))
                finish()
            }
        }
    }

    private fun sendResult(string: String) {
        val intent = Intent(this, AddLifeActivity::class.java).apply {
            putExtra("result",string)
            addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        }
        setResult(RESULT_OK, intent)
    }
}