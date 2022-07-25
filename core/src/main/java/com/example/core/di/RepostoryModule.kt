package com.example.core.di

import com.example.core.data.MovieRepository
import com.example.core.domain.repository.IMovie

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepostoryModule() {
    @Binds
    abstract fun provideRepository(repository: com.example.core.data.MovieRepository): IMovie
}