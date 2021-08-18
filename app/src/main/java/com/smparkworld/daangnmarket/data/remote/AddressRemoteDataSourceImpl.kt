package com.smparkworld.daangnmarket.data.remote

import android.location.Location
import android.util.Log
import com.smparkworld.daangnmarket.data.remote.api.AddressApi
import com.smparkworld.daangnmarket.data.remote.api.AddressAroundResponse
import com.smparkworld.daangnmarket.model.Result.Success
import com.smparkworld.daangnmarket.model.Result.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AddressRemoteDataSourceImpl @Inject constructor(
        private val addressApi: AddressApi
) : AddressRemoteDataSource{

    override suspend fun getAddressAround(
            location: Location,
            page: Int,
            size: Int
    ) = withContext(Dispatchers.IO) {
        addressApi.getAround(location.latitude, location.longitude, page, size)
    }

    override suspend fun getAddressSearch(
            search: String,
            page: Int,
            size: Int
    ) = withContext(Dispatchers.IO) {
        addressApi.getSearch(search, page, size)
    }
}