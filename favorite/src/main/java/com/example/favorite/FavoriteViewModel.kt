package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
):ViewModel()
{
    fun getAllFavoriteMovies() = movieUseCase.getAllFavoriteMovie().asLiveData()
}