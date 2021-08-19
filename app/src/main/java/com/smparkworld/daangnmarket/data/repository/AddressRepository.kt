package com.smparkworld.daangnmarket.data.repository

import android.location.Location
import androidx.paging.PagingData
import com.smparkworld.daangnmarket.model.Address
import com.smparkworld.daangnmarket.model.Result
import kotlinx.coroutines.flow.Flow

interface AddressRepository {

    suspend fun getAroundAddress(location: Location, pageSize: Int, error: suspend (Exception) -> Unit): Flow<PagingData<Address>>

    suspend fun getSearchedAddress(search: String, pageSize: Int, error: suspend (Exception) -> Unit): Flow<PagingData<Address>>
}