package com.smparkworld.daangnmarket.ui.launch

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val applicationContext: Context
) : ViewModel() {

    val addressSearch = MutableLiveData<String>()

    fun searchAddress() {
        Toast.makeText(applicationContext, addressSearch.value, Toast.LENGTH_SHORT).show()
    }
}