package com.smparkworld.daangnmarket.ui.launch

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.data.repository.AddressRepository
import com.smparkworld.daangnmarket.model.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timer

class LoginViewModel @Inject constructor(
        private val addressRepository: AddressRepository
) : ViewModel() {

    private val _sign = MutableLiveData<Boolean>()
    val sign: LiveData<Boolean> = _sign

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _addressFlow = MutableLiveData<Flow<PagingData<Address>>>()
    val addressFlow: LiveData<Flow<PagingData<Address>>> = _addressFlow

    private val _addressFail = MutableLiveData<Boolean>()
    val addressFail: LiveData<Boolean> = _addressFail

    private val _authTimer = MutableLiveData<String>()
    val authTimer: LiveData<String> = _authTimer

    private var timer: Timer? = null

    val addressSearch = MutableLiveData<String>()

    val phoneNumber = MutableLiveData<String>()

    val securityNumber = MutableLiveData<String>()

    private lateinit var selectedAddress: Address

    fun loadAroundAddress(location: Location) {

        viewModelScope.launch {

            addressSearch.value = null
            _addressFail.value = false
            _addressFlow.value = addressRepository.getAroundAddress(location, 20) {
                when (it) {
                    is IOException -> {
                        _addressFail.value = true
                        _error.value = R.string.error_failedToConnectNetwork
                    }
                    else -> {
                        _addressFail.value = true
                        _error.value = R.string.error_fatalNetwork
                    }
                }
                it.printStackTrace()
            }
        }
    }

    fun searchAddress() {

        viewModelScope.launch {

            val search = addressSearch.value
            if (!search.isNullOrEmpty()) {
                _addressFail.value = false
                _addressFlow.value = addressRepository.getSearchedAddress(search, 20) {
                    when (it) {
                        is IOException -> {
                            _addressFail.value = true
                            _error.value = R.string.error_failedToConnectNetwork
                        }
                        else -> {
                            _addressFail.value = true
                            _error.value = R.string.error_fatalNetwork
                        }
                    }
                    it.printStackTrace()
                }
            }
        }
    }

    fun setSelectedAddress(address: Address) {
        selectedAddress = address
    }

    fun authorization() {
        viewModelScope.launch {
            startTimer()

            /* SMS 발송 기능 구현 하는 곳 */
        }
    }

    fun confirmSecurityNumber() {
        viewModelScope.launch {
            
            /* 인증번호 확인 기능 구현 하는 곳, 임시로 0000일 경우 인증 성공 */
            if (securityNumber.value == "0000" && authTimer.value?.equals("00:00") == false) {
                timer?.cancel()

                _sign.value = true
            } else {
                _error.value = R.string.fragmentAuth_failedToAuthorization
            }
        }
    }

    private fun startTimer() {
        _authTimer.value = "05:00"

        var time = 300
        timer?.cancel()
        timer = timer(period = 1000) {
            if (--time >= 0) {
                _authTimer.postValue("${String.format("%02d", time / 60)}:${String.format("%02d", time % 60)}")
            } else {
                cancel()
            }
        }
    }
}