package com.smparkworld.daangnmarket.extension

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("life_category", Context.MODE_PRIVATE)

    fun getCategory(key: String, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    fun setCategory(key: String, defValue: Boolean) {
        prefs.edit().putBoolean(key, defValue).apply()
    }

    fun getAll(): MutableMap<String, *>? {
        return prefs.all
    }
}
