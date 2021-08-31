package com.smparkworld.daangnmarket.ui.main.addLife

import android.app.Application
import com.smparkworld.daangnmarket.DaangnApp

class CategoryOptionRepository(application: Application) {

    private val categoryData: MutableMap<String, *>? = DaangnApp.prefs.getAll()
    private val data = ArrayList<String>()

    fun getCategoryDataAll(): ArrayList<String> {
        categoryData?.forEach { (name, value) ->
            if (value as Boolean) {
                data.add(name)
            }
        }

        return data
    }

}