package com.tugbaolcer.foreignexchangeapp.domain.di

import com.tugbaolcer.foreignexchangeapp.domain.repository.AuthRepository
import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import com.tugbaolcer.foreignexchangeapp.domain.usecase.CryptoUseCase
import com.tugbaolcer.foreignexchangeapp.domain.usecase.LoginUseCase
import com.tugbaolcer.foreignexchangeapp.domain.usecase.RegisterUseCase
import com.tugbaolcer.foreignexchangeapp.domain.usecase.StocksUseCase
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
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ) = LoginUseCase(authRepository)

    @ViewModelScoped
    @Provides
    fun provideRegisterUseCase(
        authRepository: AuthRepository
    ) = RegisterUseCase(authRepository)

    @ViewModelScoped
    @Provides
    fun provideCryptoUseCase(
        cryptoRepository: CryptoRepository
    ) = CryptoUseCase(cryptoRepository)

    @ViewModelScoped
    @Provides
    fun provideStockUseCase(
        cryptoRepository: CryptoRepository
    ) = StocksUseCase(cryptoRepository)
}