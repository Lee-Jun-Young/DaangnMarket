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
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val addressRepository: AddressRepository
) : ViewModel() {

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _addressFlow = MutableLiveData<Flow<PagingData<Address>>>()
    val addressFlow: LiveData<Flow<PagingData<Address>>> = _addressFlow

    private val _addressFail = MutableLiveData<Boolean>()
    val addressFail: LiveData<Boolean> = _addressFail

    val addressSearch = MutableLiveData<String>()

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
}