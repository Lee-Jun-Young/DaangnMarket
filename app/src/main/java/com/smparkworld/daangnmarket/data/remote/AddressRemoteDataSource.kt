package com.smparkworld.daangnmarket.data.remote

import android.location.Location
import com.smparkworld.daangnmarket.data.remote.api.AddressAroundResponse
import com.smparkworld.daangnmarket.data.remote.api.AddressSearchResponse
import retrofit2.Response

interface AddressRemoteDataSource {

    suspend fun getAddressAround(location: Location, page: Int, size: Int): Response<AddressAroundResponse>

    suspend fun getAddressSearch(search: String, page: Int, size: Int): Response<AddressSearchResponse>
}