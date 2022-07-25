package com.example.movieyuk.di

import com.example.core.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependecies{
    fun movieUsecase(): MovieUseCase
}