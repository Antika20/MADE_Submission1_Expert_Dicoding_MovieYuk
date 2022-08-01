package com.example.core.di

import androidx.viewbinding.BuildConfig
import com.example.core.data.source.remote.network.ApiService
import com.example.core.utils.helper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
//    const val hostname = "api.themoviedb.org"
//    private val certificatePinner = CertificatePinner.Builder()
//        .add(hostname,"SHA256: oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=")
//        .add(hostname,"SHA256: JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
//        .add(hostname,"SHA256: ++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
//        .build()

    const val hostname = "api.themoviedb.org"
    private val certificatePinner = CertificatePinner.Builder()
        .add(hostname,"sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOK")
        .add(hostname,"sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA")
        .add(hostname,"sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI")
        .build()

    @Provides
    fun provideOkHttpClientMovie():OkHttpClient{
        val movieLongingInterceptor = if (BuildConfig.DEBUG){
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else{
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(movieLongingInterceptor)
            .certificatePinner(certificatePinner)
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

