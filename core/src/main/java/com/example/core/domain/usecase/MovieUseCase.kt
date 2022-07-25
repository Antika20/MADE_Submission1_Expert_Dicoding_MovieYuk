package com.example.core.domain.usecase

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.domain.model.ModelMovie
import kotlinx.coroutines.flow.Flow


interface MovieUseCase {
    fun getNowPlayingMovie():Flow<ApiResponse<List<ModelMovie>>>

    fun searchMovie(query: String):Flow<ApiResponse<List<ModelMovie>>>

    fun getAllFavoriteMovie():Flow<List<ModelMovie>>

    suspend fun insertToAllFavoriteMovie(movie: ModelMovie)

    suspend fun deleteFromAllFavoriteMovie(movie: ModelMovie)

    fun isFavoriteMovie(title:String):Flow<Boolean>
}