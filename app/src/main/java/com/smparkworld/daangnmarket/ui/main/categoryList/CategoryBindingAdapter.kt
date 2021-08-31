package com.smparkworld.daangnmarket.ui.main.categoryList

import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.model.CategoryList
import com.smparkworld.daangnmarket.ui.main.addLife.CategoryOptionAdapter

object CategoryBindingAdapter {

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