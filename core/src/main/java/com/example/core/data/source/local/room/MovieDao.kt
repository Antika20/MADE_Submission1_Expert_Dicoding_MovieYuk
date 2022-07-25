package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entitity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Delete
     fun deleteMovies(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertToFavorite(movieEntity: MovieEntity)

    @Query("SELECT EXISTS(SELECT * FROM `movie` WHERE title = :title )")
    fun getFavoriteMovie(title:String): Flow<Boolean>


}