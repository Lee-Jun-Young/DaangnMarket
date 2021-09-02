package com.smparkworld.daangnmarket.ui.main.life

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.daangnmarket.databinding.FragmentLifeItemBinding
import com.smparkworld.daangnmarket.model.LifeList

class LifeAdapter(val context: Context, data: ArrayList<LifeList>) :
    RecyclerView.Adapter<LifeAdapter.ViewHolder>() {

    var items = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentLifeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ViewHolder(private val binding: FragmentLifeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data : LifeList) {
            binding.data = data
        }
    }


}