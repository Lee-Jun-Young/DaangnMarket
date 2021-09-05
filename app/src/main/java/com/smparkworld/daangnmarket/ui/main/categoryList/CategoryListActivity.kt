package com.smparkworld.daangnmarket.ui.main.categoryList

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.ActivityCategoryListBinding
import com.smparkworld.daangnmarket.model.CategoryList


class CategoryListActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var categoryBinding: ActivityCategoryListBinding
    // private lateinit var categoryListViewModel: CategoryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_list)
        categoryBinding.lifeCategory = this@CategoryListActivity
        categoryBinding.lifecycleOwner = this

        setRecyclerView()

        // categoryListViewModel =  ViewModelProvider(this, CategoryListViewModel.Factory(application)).get(CategoryListViewModel::class.java)

    }

    private fun setRecyclerView() {

        val categoryList = arrayListOf<CategoryList>(
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_1), R.drawable.category_1_true, R.drawable.category_1),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_2), R.drawable.category_2_true, R.drawable.category_2),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_3), R.drawable.category_3_true, R.drawable.category_3),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_4), R.drawable.category_4_true, R.drawable.category_4),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_5), R.drawable.category_5_true, R.drawable.category_5),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_6), R.drawable.category_6_true, R.drawable.category_6),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_7), R.drawable.category_7_true, R.drawable.category_7),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_8), R.drawable.category_8_true, R.drawable.category_8),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_9), R.drawable.category_9_true, R.drawable.category_9),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_10), R.drawable.category_10_true, R.drawable.category_10),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_11), R.drawable.category_11_true, R.drawable.category_11),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_12), R.drawable.category_12_true, R.drawable.category_12),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_13), R.drawable.category_13_true, R.drawable.category_13),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_14), R.drawable.category_14_true, R.drawable.category_14),
            CategoryList(applicationContext.getString(R.string.activityCategoryList_categoryName_15), R.drawable.category_15_true, R.drawable.category_15)
        )

        val adapter = CategoryAdapter(this, categoryList)
        categoryBinding.recyclerview.layoutManager = GridLayoutManager(this, 3)
        categoryBinding.recyclerview.adapter = adapter
        categoryBinding.recyclerview.setHasFixedSize(true)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back ->{
                finish()
            }
        }
    }

}