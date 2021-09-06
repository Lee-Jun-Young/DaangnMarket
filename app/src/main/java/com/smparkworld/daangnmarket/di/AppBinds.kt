package com.smparkworld.daangnmarket.di

import androidx.lifecycle.ViewModelProvider
import com.smparkworld.daangnmarket.data.local.UserLocalDataSource
import com.smparkworld.daangnmarket.data.local.UserLocalDataSourceImpl
import com.smparkworld.daangnmarket.data.remote.AddressRemoteDataSource
import com.smparkworld.daangnmarket.data.remote.AddressRemoteDataSourceImpl
import com.smparkworld.daangnmarket.data.remote.UserRemoteDataSource
import com.smparkworld.daangnmarket.data.remote.UserRemoteDataSourceImpl
import com.smparkworld.daangnmarket.data.repository.AddressRepository
import com.smparkworld.daangnmarket.data.repository.AddressRepositoryImpl
import com.smparkworld.daangnmarket.data.repository.UserRepository
import com.smparkworld.daangnmarket.data.repository.UserRepositoryImpl
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
    abstract fun bindUserRemoteDataSource(
            userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

    @Binds
    abstract fun bindUserLocalDataSource(
            userLocalDataSourceImpl: UserLocalDataSourceImpl
    ): UserLocalDataSource

    @Binds
    abstract fun bindAddressRepository(
            addressRepositoryImpl: AddressRepositoryImpl
    ): AddressRepository

    @Binds
    abstract fun bindUserRepository(
            userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}