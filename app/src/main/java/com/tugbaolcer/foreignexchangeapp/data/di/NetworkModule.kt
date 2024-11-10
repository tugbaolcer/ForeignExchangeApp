package com.tugbaolcer.foreignexchangeapp.data.di

import com.tugbaolcer.foreignexchangeapp.BuildConfig
import com.tugbaolcer.foreignexchangeapp.data.dto.AppApi
import com.tugbaolcer.foreignexchangeapp.data.repository.CryptoRepositoryImpl
import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import com.tugbaolcer.foreignexchangeapp.domain.usecase.CryptoUseCase
import com.tugbaolcer.foreignexchangeapp.util.Constants.BASE_URL
import com.tugbaolcer.foreignexchangeapp.util.Constants.CONTENT_TYPE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            val requestBuilder = request.newBuilder()
                .addHeader("authorization", BuildConfig.API_KEY)
                .addHeader("content-type", CONTENT_TYPE)

            request = requestBuilder.build()

            return@Interceptor chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideCryptoAPI(authInterceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor,): AppApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCryptoRepository(appApi: AppApi): CryptoRepository {
        return CryptoRepositoryImpl(appApi)
    }

    @Provides
    @Singleton
    fun provideCryptoUseCase(cryptoRepository: CryptoRepository): CryptoUseCase {
        return CryptoUseCase(cryptoRepository)
    }
}
