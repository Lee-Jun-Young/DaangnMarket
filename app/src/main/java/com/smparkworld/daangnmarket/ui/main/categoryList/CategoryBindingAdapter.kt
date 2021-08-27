package com.smparkworld.daangnmarket.ui.main.categoryList

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.model.CategoryList

object CategoryBindingAdapter {
    /*
    @BindingAdapter("bind_categoryList")
    @JvmStatic
    fun bindCategoryList(recyclerView: RecyclerView, items: ArrayList<CategoryList>?) {
        if (recyclerView.adapter == null) {
            val lm = GridLayoutManager(recyclerView.context, 3)
            val adapter = items?.let { CategoryAdapter(it) }
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }
    */

    @BindingAdapter("bind_categoryImage")
    @JvmStatic
    fun loadImage(imageView: ImageView, categoryList: CategoryList) {
        val check = DaangnApp.prefs.getCategory(categoryList.name, false)

        if (check) {
            Glide.with(imageView.context).load(categoryList.select)
                .into(imageView)
        } else {
            Glide.with(imageView.context).load(categoryList.unselect)
                .into(imageView)
        }
    }

}