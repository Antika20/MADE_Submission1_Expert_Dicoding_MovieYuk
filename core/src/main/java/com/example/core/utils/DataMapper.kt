package com.example.core.utils


import com.example.core.data.source.local.entitity.MovieEntity
import com.example.core.data.source.remote.response.ResultsItem
import com.example.core.domain.model.ModelMovie


object DataMapper {



    fun mapEntitiesMovieToDomain(inputMovie:List<MovieEntity>): List<ModelMovie> =
        inputMovie.map {
            ModelMovie(
                movieId = it.movieId,
                title = it.title,
                release_date = it.release_date,
                poster_path = it.poster_path,
                overview = it.overview,
                popularity = it.popularity,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntityMovie(inputMovie:ModelMovie) = MovieEntity(
        movieId = inputMovie.movieId,
        title = inputMovie.title,
        release_date = inputMovie.release_date,
        poster_path = inputMovie.poster_path,
        overview = inputMovie.overview,
        popularity = inputMovie.popularity,
        isFavorite = inputMovie.isFavorite
    )

    fun setToolbar (actionBar:androidx.appcompat.app.ActionBar?,titleName:String,subTitleName:String){
        actionBar?.apply {
            title = titleName
            subtitle = subTitleName
        }
    }

}