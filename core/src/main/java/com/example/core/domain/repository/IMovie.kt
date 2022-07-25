package com.example.core.domain.repository

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.domain.model.ModelMovie
import kotlinx.coroutines.flow.Flow

interface IMovie {
    fun getNowPlaying(): Flow<ApiResponse<List<ModelMovie>>>

    fun getFavoriteMovie() :Flow<List<ModelMovie>>

    suspend fun insertToFavoriteMovie(modelMovie: ModelMovie)

    suspend fun deleteFromFavoriteMovie(modelMovie: ModelMovie)

    fun isFavorite(title:String):Flow<Boolean>

    fun searchMovie(query:String):Flow<ApiResponse<List<ModelMovie>>>
}