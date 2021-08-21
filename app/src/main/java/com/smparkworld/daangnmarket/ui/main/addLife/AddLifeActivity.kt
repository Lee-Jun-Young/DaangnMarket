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
import com.smparkworld.daangnmarket.DaangnApp
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

        init()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        saveTempData()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back -> {
                saveTempData()
                finish()
            }
            R.id.ll_categoryOption -> {
                val intent = Intent(this, CategoryOptionActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                getResultText.launch(intent)
                Log.d("test!!", addLifeBinding.tvCategoryOptionText.text as String)
            }
        }
    }

    private fun init(){
        val tempData: String? = DaangnApp.prefs.getData("id","N")

        if(tempData == "Y"){
            addLifeBinding.tvCategoryOptionText.text = DaangnApp.prefs.getData("categoryOption","")
            addLifeBinding.etAddLife.setText(DaangnApp.prefs.getData("addLifeText",""))
        }

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {result ->
            if(result.resultCode == RESULT_OK){
                val mString = result.data?.getStringExtra("result")
                if (mString != null) {
                    addLifeBinding.tvCategoryOptionText.text = mString
                    DaangnApp.prefs.setData("categoryOption",mString)
                }
            }
        }
    }

    private fun saveTempData(){
        if(addLifeBinding.tvCategoryOptionText.text == "게시글의 주제를 선택해주세요" && addLifeBinding.etAddLife.text == null) {
            DaangnApp.prefs.setData("id", "N")
            DaangnApp.prefs.setData("categoryOption", "")
            DaangnApp.prefs.setData("addLifeText", "")
        }else{
            DaangnApp.prefs.setData("id", "Y")
            DaangnApp.prefs.setData("categoryOption", addLifeBinding.tvCategoryOptionText.text.toString())
            DaangnApp.prefs.setData("addLifeText", addLifeBinding.etAddLife.text.toString())
        }
    }
}