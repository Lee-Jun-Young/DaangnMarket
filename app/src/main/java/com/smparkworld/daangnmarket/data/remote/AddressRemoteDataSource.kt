package com.smparkworld.daangnmarket.data.remote

import android.location.Location
import com.smparkworld.daangnmarket.data.remote.api.AddressAroundResponse
import retrofit2.Response

interface AddressRemoteDataSource {

    suspend fun getAroundAddress(location: Location, page: Int, size: Int): Response<AddressAroundResponse>
}