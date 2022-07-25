package com.example.core.di

import androidx.viewbinding.BuildConfig
import com.example.core.data.source.remote.network.ApiService
import com.example.core.utils.helper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideOkHttpClientMovie():OkHttpClient{
        val movieLongingInterceptor = if (BuildConfig.DEBUG){
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else{
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(movieLongingInterceptor)
            .build()
    }

    @Provides
    fun provideConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ):Retrofit = Retrofit.Builder().baseUrl(helper.BASE_URL).client(okHttpClient).addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}

