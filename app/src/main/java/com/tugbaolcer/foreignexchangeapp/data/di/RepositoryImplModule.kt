package com.tugbaolcer.foreignexchangeapp.data.di

import com.tugbaolcer.foreignexchangeapp.data.repository.CryptoRepositoryImpl
import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryImplModule {

    @Binds
    abstract fun bindCryptoRepository(
        cryptoRepositoryImpl: CryptoRepositoryImpl
    ): CryptoRepository


}
