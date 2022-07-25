package com.example.movieyuk.detailMovie


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ModelMovie
import com.example.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
):ViewModel(){

    private var _isFavoriteMovie = MutableLiveData<Boolean>()
    val isMovieFavorite get() = _isFavoriteMovie

    private val _IMovie = MutableLiveData<ModelMovie>()
    val Movies get() = _IMovie


    fun getDetailMovies() = movieUseCase.getNowPlayingMovie().asLiveData()


    fun insertMovieToFavorite(movie: ModelMovie) = viewModelScope.launch {
        movieUseCase.insertToAllFavoriteMovie(movie)
    }

    fun removedMovieToFavorite(movie: ModelMovie) = viewModelScope.launch {
        movieUseCase.deleteFromAllFavoriteMovie(movie)
    }

    fun isFavoriteMovie(title:String) = viewModelScope.launch {
        movieUseCase.isFavoriteMovie(title).collect{
            _isFavoriteMovie.value =it
        }
    }
}