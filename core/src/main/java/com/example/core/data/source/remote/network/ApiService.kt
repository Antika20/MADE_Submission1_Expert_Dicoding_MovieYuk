package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getPlaying(
        @Query("api_key")apiKey:String = "d9a122f6f8a9995b9bd2d4631dfc5fe9",
        @Query("languange")language:String = "en-Us",
        @Query("page")page:Int = 1
    ): Response<MovieResponse>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key")apiKey: String = "d9a122f6f8a9995b9bd2d4631dfc5fe9",
        @Query("languange")language:String = "en-Us",
        @Query("query") query: String,
        @Query("page")page:Int = 1,
        @Query("include_adult") include_adult:Boolean = false
    ): Response<MovieResponse>
}