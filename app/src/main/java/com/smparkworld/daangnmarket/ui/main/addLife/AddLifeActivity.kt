package com.smparkworld.daangnmarket.ui.main.addLife

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.windsekirun.naraeimagepicker.Constants
import com.github.windsekirun.naraeimagepicker.NaraeImagePicker
import com.github.windsekirun.naraeimagepicker.impl.OnPickResultListener
import com.github.windsekirun.naraeimagepicker.item.PickerSettingItem
import com.github.windsekirun.naraeimagepicker.item.enumeration.ViewMode
import com.google.android.material.snackbar.Snackbar
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.ActivityAddLifeBinding
import com.smparkworld.daangnmarket.ui.main.life.LifeFragment


class AddLifeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var addLifeBinding: ActivityAddLifeBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    var imageUrlList = ArrayList<String>()

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
        when (v?.id) {
            R.id.iv_back -> {
                saveTempData()
                finish()
            }
            R.id.ll_categoryOption -> {
                val intent = Intent(this, CategoryOptionActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                getResultText.launch(intent)
            }
            R.id.iv_addLife_image -> {
                initPickerView()
            }
            R.id.btn_addLife -> {
                Log.d("test!!", "추가버튼 클릭")
                Toast.makeText(this, "성공적으로 추가되었습니다.", Toast.LENGTH_SHORT).show()
                DaangnApp.prefs.setData("id", "N")
                finish()
            }
        }
    }

    private fun initPickerView() {
        val settingItem = PickerSettingItem().apply {
            pickLimit = Constants.LIMIT_UNLIMITED
            viewMode = ViewMode.FolderView
            enableDetailMode = true
            uiSetting.enableUpInParentView = true
            uiSetting.pickerTitle = "Custom Picker Title"
            uiSetting.fileSpanCount = 3
            uiSetting.folderSpanCount = 2
        }

        NaraeImagePicker.instance.start(this, settingItem, object : OnPickResultListener {
            @SuppressLint("SetTextI18n")
            override fun onSelect(resultCode: Int, imageList: ArrayList<String>) {
                if (resultCode == NaraeImagePicker.PICK_SUCCESS) {
                    Log.d("test!!", "이미지 선택 성공")
                    Log.d("test!!", "imageList : $imageList")

                    if (imageList.isNotEmpty())
                        addLifeBinding.imageRecyclerview.isVisible = true
                    else
                        addLifeBinding.imageRecyclerview.isVisible = true

                    for (i in 0 until imageList.size)
                        imageUrlList.add(imageList[i])

                    val adapter = SelectImageAdapter(imageUrlList)
                    val linearLayoutManager = LinearLayoutManager(applicationContext)
                    linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                    addLifeBinding.imageRecyclerview.layoutManager = linearLayoutManager
                    addLifeBinding.imageRecyclerview.adapter = adapter
                    addLifeBinding.imageRecyclerview.setHasFixedSize(true)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun init() {
        val tempData: String? = DaangnApp.prefs.getData("id", "N")

        if (tempData == "Y") {
            setDialog()
        }

        addLifeBinding.btnAddLife.isEnabled = addLifeBinding.etAddLife.text.toString().isNotEmpty()

        addLifeBinding.etAddLife.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                addLifeBinding.btnAddLife.isEnabled =
                    addLifeBinding.etAddLife.text.toString().isNotEmpty()
            }
        })

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val mString = result.data?.getStringExtra("result")
                if (mString != null) {
                    addLifeBinding.tvCategoryOptionText.text = mString
                    DaangnApp.prefs.setData("categoryOption", mString)
                }
            }
        }

    }

    private fun saveTempData() {
        if (addLifeBinding.tvCategoryOptionText.text == applicationContext.getString(R.string.activityAddLife_categoryOptionText) && addLifeBinding.etAddLife.text.toString() == "") {
            DaangnApp.prefs.setData("id", "N")
            DaangnApp.prefs.setData("categoryOption", " ")
            DaangnApp.prefs.setData("addLifeText", " ")
        } else {
            DaangnApp.prefs.setData("id", "Y")
            DaangnApp.prefs.setData(
                "categoryOption",
                addLifeBinding.tvCategoryOptionText.text.toString()
            )
            DaangnApp.prefs.setData("addLifeText", addLifeBinding.etAddLife.text.toString())
        }
    }

    private fun setDialog() {
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.addlife_custom_dialog, null)

        val alertDialog = AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()

        view.findViewById<Button>(R.id.btn_positive).setOnClickListener {
            addLifeBinding.tvCategoryOptionText.text = DaangnApp.prefs.getData("categoryOption", "")
            addLifeBinding.etAddLife.setText(DaangnApp.prefs.getData("addLifeText", ""))
            alertDialog.dismiss()
        }

        view.findViewById<Button>(R.id.btn_negative).setOnClickListener {
            addLifeBinding.tvCategoryOptionText.setText(R.string.activityAddLife_categoryOptionText)
            addLifeBinding.etAddLife.setText("")
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

}