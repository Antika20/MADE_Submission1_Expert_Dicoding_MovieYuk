package com.example.core.domain.usecase

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.domain.model.ModelMovie
import com.example.core.domain.repository.IMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val allMovieRepository: IMovie
): MovieUseCase {

    // Networking
    override fun getNowPlayingMovie():Flow<ApiResponse<List<ModelMovie>>> =
        allMovieRepository.getNowPlaying()

    override fun searchMovie(query: String): Flow<ApiResponse<List<ModelMovie>>> =
        allMovieRepository.searchMovie(query)


    override fun getAllFavoriteMovie(): Flow<List<ModelMovie>> =
      allMovieRepository.getFavoriteMovie()


    override suspend fun insertToAllFavoriteMovie(movie: ModelMovie) =
      allMovieRepository.insertToFavoriteMovie(movie)


    override suspend fun deleteFromAllFavoriteMovie(movie: ModelMovie) =
      allMovieRepository.deleteFromFavoriteMovie(movie)


    override fun isFavoriteMovie(title:String): Flow<Boolean> =
        allMovieRepository.isFavorite(title)


}