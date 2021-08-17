package com.smparkworld.daangnmarket.data.repository

import android.location.Location
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.smparkworld.daangnmarket.data.remote.AddressRemoteDataSource
import com.smparkworld.daangnmarket.model.Result.Success
import com.smparkworld.daangnmarket.model.Result.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
        private val remoteDataSource: AddressRemoteDataSource
) : AddressRepository {

    override suspend fun getAroundAddress(
            location: Location,
            pageSize: Int,
            error: (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {

        return@withContext Pager(
                PagingConfig(pageSize = pageSize)
        ) {
            AddressPagingSource(remoteDataSource, location, pageSize) {
                if (it is HttpException && it.code() == 401) {
                    refreshAccessToken()
                } else {
                    error(it)
                }
            }
        }.flow
    }

    override suspend fun getSearchedAddress(
            search: String,
            pageSize: Int
    ) = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }

    private fun refreshAccessToken() {

    }

}