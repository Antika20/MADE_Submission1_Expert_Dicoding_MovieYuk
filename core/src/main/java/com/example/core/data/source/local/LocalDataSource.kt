package com.example.core.data.source.local

import com.example.core.data.source.local.entitity.MovieEntity
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    fun getAllAnyMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun insertToFavoriteMovie(modelEntity: MovieEntity) = movieDao.insertToFavorite(modelEntity)

    fun deleteToFavorite(modelEntity: MovieEntity) = movieDao.deleteMovies(modelEntity)

    fun isFavoriteMovie(title:String) = movieDao.getFavoriteMovie(title)

}