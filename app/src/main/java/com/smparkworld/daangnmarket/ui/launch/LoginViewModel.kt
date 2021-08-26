package com.smparkworld.daangnmarket.ui.launch

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.insertHeaderItem
import androidx.paging.map
import com.smparkworld.daangnmarket.R
import com.smparkworld.daangnmarket.data.repository.AddressRepository
import com.smparkworld.daangnmarket.data.repository.UserRepository
import com.smparkworld.daangnmarket.model.Address
import com.smparkworld.daangnmarket.model.AddressModel
import com.smparkworld.daangnmarket.model.Result.Success
import com.smparkworld.daangnmarket.model.Result.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timer

class LoginViewModel @Inject constructor(
        private val userRepository: UserRepository,
        private val addressRepository: AddressRepository
) : ViewModel() {

    // true면 처음 회원가입한 유저, false면 소유권 확인해야 하는 유저.
    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _addressFlow = MutableLiveData<Flow<PagingData<AddressModel>>>()
    val addressFlow: LiveData<Flow<PagingData<AddressModel>>> = _addressFlow

    private val _addressFail = MutableLiveData<Boolean>()
    val addressFail: LiveData<Boolean> = _addressFail

    private val _selectedAddress = MutableLiveData<Address>()
    val selectedAddress: LiveData<Address> = _selectedAddress

    private val _authTimer = MutableLiveData<String>()
    val authTimer: LiveData<String> = _authTimer

    private var timer: Timer? = null

    val addressSearch = MutableLiveData<String>()

    val phoneNumber = MutableLiveData<String>()

    val securityNumber = MutableLiveData<String>()


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
        _selectedAddress.value = address
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

                val phone = phoneNumber.value!!.replace(" ", "")
                val address = selectedAddress.value!!.id

                val result = userRepository.login(phone, address)
                if (result is Success) {
                    timer?.cancel()
                    _login.value = result.data
                } else {
                    (result as Error).printStackTrace()
                    _error.value = R.string.error_failedToConnectNetwork
                }
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