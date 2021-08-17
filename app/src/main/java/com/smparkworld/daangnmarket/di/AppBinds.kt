package com.smparkworld.daangnmarket.di

import androidx.lifecycle.ViewModelProvider
import com.smparkworld.daangnmarket.data.remote.AddressRemoteDataSource
import com.smparkworld.daangnmarket.data.remote.AddressRemoteDataSourceImpl
import com.smparkworld.daangnmarket.data.repository.AddressRepository
import com.smparkworld.daangnmarket.data.repository.AddressRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppBinds {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindAddressRemoteDataSource(
            addressRemoteDataSourceImpl: AddressRemoteDataSourceImpl
    ): AddressRemoteDataSource

    @Binds
    abstract fun bindAddressRepository(
            addressRepositoryImpl: AddressRepositoryImpl
    ): AddressRepository
}