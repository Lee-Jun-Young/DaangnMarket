package com.smparkworld.daangnmarket.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smparkworld.daangnmarket.data.repository.UserRepository
import com.smparkworld.daangnmarket.model.Result.Success
import com.smparkworld.daangnmarket.model.Result.Error
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
        private val userRepository: UserRepository
) : ViewModel() {

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    fun loginWithToken() {

        viewModelScope.launch {

            val result = userRepository.loginWithToken()
            if (result is Success) {
                _login.value = result.data
            } else {
                (result as Error).printStackTrace()
                _login.value = false
            }
        }
    }

}