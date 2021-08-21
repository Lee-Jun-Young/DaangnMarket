package com.smparkworld.daangnmarket.extension

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val lifePrefs: SharedPreferences =
        context.getSharedPreferences("life_category", Context.MODE_PRIVATE)

    private val addLifePrefs: SharedPreferences =
        context.getSharedPreferences("addLife_data",Context.MODE_PRIVATE)

    fun getCategory(key: String, defValue: Boolean): Boolean {
        return lifePrefs.getBoolean(key, defValue)
    }

    fun setCategory(key: String, defValue: Boolean) {
        lifePrefs.edit().putBoolean(key, defValue).apply()
    }

    fun getAll(): MutableMap<String, *>? {
        return lifePrefs.all
    }

    fun getData(key: String, defValue: String): String? {
        return addLifePrefs.getString(key, defValue)
    }

    fun setData(key: String, defValue: String) {
        addLifePrefs.edit().putString(key, defValue).apply()
    }

    fun getAllData(): MutableMap<String, *>? {
        return addLifePrefs.all
    }
}
