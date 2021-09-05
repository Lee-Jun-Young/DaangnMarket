package com.smparkworld.daangnmarket.ui.main.addLife

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CategoryOptionViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryOptionRepository = CategoryOptionRepository(application)
    private val categoryData = categoryOptionRepository.getCategoryDataAll()

    fun getAll() : ArrayList<String> {
        return this.categoryData
    }
}