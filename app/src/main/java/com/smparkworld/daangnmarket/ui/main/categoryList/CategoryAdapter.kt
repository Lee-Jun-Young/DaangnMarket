package com.smparkworld.daangnmarket.ui.main.categoryList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.smparkworld.daangnmarket.DaangnApp
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.CategorylistItemBinding
import com.smparkworld.daangnmarket.model.CategoryList

class CategoryAdapter(private var context: Context, categoryList: ArrayList<CategoryList>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val categoryList = categoryList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategorylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temp = DaangnApp.prefs.getCategory(categoryList[position].name, false)

        holder.bind(categoryList[position],temp)

        holder.itemView.setOnClickListener {
            if (temp) {
                DaangnApp.prefs.setCategory(categoryList[position].name, false)
                Snackbar.make(holder.itemView, categoryList[position].name + context.getString(R.string.activityCategoryList_unselect_snackBar_format), Snackbar.LENGTH_SHORT).show()
                holder.bind(categoryList[position],temp)
                notifyDataSetChanged()
            } else {
                DaangnApp.prefs.setCategory(categoryList[position].name, true)
                Snackbar.make(holder.itemView, categoryList[position].name + context.getString(R.string.activityCategoryList_select_snackBar_format), Snackbar.LENGTH_SHORT).show()
                holder.bind(categoryList[position],temp)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.count()
    }

    class ViewHolder(private val binding: CategorylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryList: CategoryList, check: Boolean) {
            if(check)
                binding.ivCategory.setImageResource(categoryList.select)
            else
                binding.ivCategory.setImageResource(categoryList.unselect)
        }
    }

}