package com.smparkworld.daangnmarket.data.repository

import android.location.Location
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.smparkworld.daangnmarket.data.remote.AddressRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
        private val remoteDataSource: AddressRemoteDataSource
) : AddressRepository {

    override suspend fun getAroundAddress(
            location: Location,
            pageSize: Int,
            error: suspend (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {

        return@withContext Pager(
                PagingConfig(pageSize = pageSize)
        ) {
            AddressAroundPagingSource(remoteDataSource, location, pageSize, error)
        }.flow
    }

    override suspend fun getSearchedAddress(
            search: String,
            pageSize: Int,
            error: suspend (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {

        return@withContext Pager(
                PagingConfig(pageSize = pageSize)
        ) {
            AddressSearchPagingSource(remoteDataSource, search, pageSize, error)
        }.flow
    }

    private fun refreshAccessToken() {}
}