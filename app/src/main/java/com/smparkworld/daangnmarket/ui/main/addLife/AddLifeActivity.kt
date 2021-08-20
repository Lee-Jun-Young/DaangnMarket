package com.smparkworld.daangnmarket.ui.main.addLife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.ActivityAddLifeBinding
import com.smparkworld.daangnmarket.databinding.FragmentLifeBinding
import com.smparkworld.daangnmarket.ui.main.categoryList.CategoryListActivity

class AddLifeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var addLifeBinding: ActivityAddLifeBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addLifeBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_life)
        addLifeBinding.addLife = this@AddLifeActivity

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {result ->
            if(result.resultCode == RESULT_OK){
                val mString = result.data?.getStringExtra("result")
                if (mString != null) {
                    addLifeBinding.tvCategoryOptionText.text = mString
                    Log.d("test!!",mString)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back -> {
                finish()
            }
            R.id.ll_categoryOption -> {
                val intent = Intent(this, CategoryOptionActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                getResultText.launch(intent)
            }
        }
    }
}