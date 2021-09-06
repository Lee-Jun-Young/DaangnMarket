package com.smparkworld.daangnmarket.data.repository

import android.location.Location
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.smparkworld.daangnmarket.data.remote.AddressRemoteDataSource
import com.smparkworld.daangnmarket.model.Address
import retrofit2.HttpException

class AddressAroundPagingSource(
        private val remoteDataSource: AddressRemoteDataSource,
        private val location: Location,
        private val pageSize: Int
) : PagingSource<Int, Address>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Address> {
        try {
            val nextPage = params.key ?: 0

            val response = remoteDataSource.getAddressAround(location, nextPage, pageSize)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return LoadResult.Page(
                            data = body.data,
                            prevKey = null,
                            nextKey = body.nextPage
                    )
                } else {
                    throw NullPointerException("the response body is null.")
                }
            } else {
                throw HttpException(response)
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Address>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}