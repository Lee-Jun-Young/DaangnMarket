package com.smparkworld.daangnmarket.extension

import android.widget.EditText
import androidx.databinding.BindingAdapter


@BindingAdapter("onAction")
fun onAction(view: EditText, done: Function0<Unit>) {
    view.setOnEditorActionListener { _, _, _ ->
        done.invoke()
        return@setOnEditorActionListener false
    }
}