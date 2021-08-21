package com.smparkworld.daangnmarket.ui.main.addLife

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.daangnmarket.databinding.CategoryoptionItemBinding

class CategoryOptionAdapter(
    private var context: Context,
    val data: ArrayList<String>,
) :
    RecyclerView.Adapter<CategoryOptionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryoptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryOptionAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])

        holder.itemView.setOnClickListener {
            val s = data[position]
            val intent = Intent(context, AddLifeActivity::class.java).apply {
                putExtra("result",s)
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            }
            (context as Activity).setResult(RESULT_OK, intent)
            (context as Activity).finish()
        }
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    class ViewHolder(private val binding: CategoryoptionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.tvCategoryOptions.text = data
        }
    }

}