package com.smparkworld.daangnmarket.ui.main.addLife

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.databinding.SelectImageItemBinding

class SelectImageAdapter(private val imageUrlList: ArrayList<String>) :
    RecyclerView.Adapter<SelectImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SelectImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageUrlList[position])

        holder.itemView.findViewById<ImageView>(R.id.iv_removeItem).setOnClickListener {
            imageUrlList.removeAt(holder.bindingAdapterPosition)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return imageUrlList.size
    }

    class ViewHolder(private val binding: SelectImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(Url: String) {
            Glide.with(itemView)
                .asBitmap()
                .load(Url)
                .into(binding.ivSelectImage)
        }
    }

}