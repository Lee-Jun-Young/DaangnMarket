package com.smparkworld.daangnmarket.ui.main.life

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.daangnmarket.databinding.FragmentLifeItemBinding
import com.smparkworld.daangnmarket.model.CategoryList
import com.smparkworld.daangnmarket.model.LifeList
import com.smparkworld.daangnmarket.ui.main.addLife.AddLifeActivity
import kotlin.reflect.typeOf

class LifeAdapter(val context: Context, data: List<LifeList>) :
    RecyclerView.Adapter<LifeAdapter.ViewHolder>() {

    var items = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentLifeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailLifeActivity::class.java).apply {
                putExtra("detailData", items[position])
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ViewHolder(private val binding: FragmentLifeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LifeList) {
            binding.data = data
        }
    }

}