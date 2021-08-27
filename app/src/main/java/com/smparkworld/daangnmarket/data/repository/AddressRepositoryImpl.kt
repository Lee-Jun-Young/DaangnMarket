package com.smparkworld.daangnmarket.data.repository

import android.location.Location
import androidx.paging.*
import com.smparkworld.daangnmarket.data.remote.AddressRemoteDataSource
import com.smparkworld.daangnmarket.model.AddressModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
        private val remoteDataSource: AddressRemoteDataSource
) : AddressRepository {

    override suspend fun getAroundAddress(
            location: Location,
            pageSize: Int
    ) = withContext(Dispatchers.IO) {

        return@withContext Pager(
                PagingConfig(pageSize = pageSize)
        ) {
            AddressAroundPagingSource(remoteDataSource, location, pageSize)
        }.flow.map {
            it.map { item -> AddressModel.Item(item) as AddressModel }
                .insertHeaderItem(item = AddressModel.Header("근처동네"))
                .insertSeparators { before, after ->
                    if (before is AddressModel.Item && after is AddressModel.Item)
                        AddressModel.Separator
                    else null
                }
        }
    }

    override suspend fun getSearchedAddress(
            search: String,
            pageSize: Int
    ) = withContext(Dispatchers.IO) {

        return@withContext Pager(
                PagingConfig(pageSize = pageSize)
        ) {
            AddressSearchPagingSource(remoteDataSource, search, pageSize)
        }.flow.map {
            it.map { item -> AddressModel.Item(item) as AddressModel }
                .insertHeaderItem(item = AddressModel.Header("'$search' 검색결과"))
                .insertSeparators { before, after ->
                    if (before is AddressModel.Item && after is AddressModel.Item)
                        AddressModel.Separator
                    else null
                }
        }
    }
}