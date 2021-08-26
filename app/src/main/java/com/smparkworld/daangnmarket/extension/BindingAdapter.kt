package com.smparkworld.daangnmarket.extension

import android.widget.EditText
import android.widget.LinearLayout.VERTICAL
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("onAction")
fun onAction(view: EditText, done: Function0<Unit>) {
    view.setOnEditorActionListener { _, _, _ ->
        done.invoke()
        return@setOnEditorActionListener false
    }
}