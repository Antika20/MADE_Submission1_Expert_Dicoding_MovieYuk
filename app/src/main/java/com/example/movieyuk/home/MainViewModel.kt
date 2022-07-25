package com.example.movieyuk.home

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
class MainViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase

):ViewModel() {

    private var _movie = MutableLiveData<List<ModelMovie>>()
    val movie get() = _movie


    val moviePlayingNow = movieUseCase.getNowPlayingMovie().asLiveData()
    fun searchMovieNow(title:String) = movieUseCase.searchMovie(title).asLiveData()


}