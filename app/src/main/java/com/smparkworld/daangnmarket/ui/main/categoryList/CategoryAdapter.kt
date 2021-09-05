package com.smparkworld.daangnmarket.ui.main.categoryList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.CategorylistItemBinding
import com.smparkworld.daangnmarket.model.CategoryList

class CategoryAdapter(val context: Context, data: ArrayList<CategoryList>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var items = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategorylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            val temp = DaangnApp.prefs.getCategory(items[position].name, false)

            if (temp) {
                DaangnApp.prefs.setCategory(items[position].name, false)
                Snackbar.make(holder.itemView, items[position].name + context.getString(R.string.activityCategoryList_unselect_snackBar_format), Snackbar.LENGTH_SHORT).show()
                holder.bind(items[position])
            } else {
                DaangnApp.prefs.setCategory(items[position].name, true)
                Snackbar.make(holder.itemView, items[position].name + context.getString(R.string.activityCategoryList_select_snackBar_format), Snackbar.LENGTH_SHORT).show()
                holder.bind(items[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ViewHolder(private val binding: CategorylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryList: CategoryList) {
            binding.categoryList = categoryList
        }
    }

}