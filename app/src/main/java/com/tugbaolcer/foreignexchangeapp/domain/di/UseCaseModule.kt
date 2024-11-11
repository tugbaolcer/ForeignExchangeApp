package com.tugbaolcer.foreignexchangeapp.domain.di

import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import com.tugbaolcer.foreignexchangeapp.domain.usecase.CryptoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideCryptoUseCase(
        cryptoRepository: CryptoRepository
    ) = CryptoUseCase(cryptoRepository)

}