package com.tugbaolcer.foreignexchangeapp.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tugbaolcer.foreignexchangeapp.BuildConfig
import com.tugbaolcer.foreignexchangeapp.data.network.adapter.SafeDoubleAdapter
import com.tugbaolcer.foreignexchangeapp.data.network.dto.AppApi
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

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Double::class.java, SafeDoubleAdapter()) // Özel Adapter
            .create()
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
    fun provideCryptoAPI(
        gson: Gson,
        authInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): AppApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AppApi::class.java)
    }
}
