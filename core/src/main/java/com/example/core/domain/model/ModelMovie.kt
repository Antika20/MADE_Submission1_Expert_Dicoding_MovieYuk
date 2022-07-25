package com.example.core.domain.model

import android.os.Parcelable
import com.example.core.data.source.remote.response.ResultsItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelMovie(
    val movieId: Int,
    val title:String,
    val release_date:String,
    val poster_path:String,
    val overview:String,
    val popularity:Double,
    var isFavorite: Boolean = false
):Parcelable

fun List<ResultsItem>.toListMovie():MutableList<ModelMovie>{
    val listMovie = mutableListOf<ModelMovie>()
    this.forEach {
        listMovie.add(
            ModelMovie(
                it.id?:-1,
                it.title?:"-",
                it.releaseDate?:"-",
                it.posterPath?:"-",
                it.overview?:"-",
                it.popularity?:0.0,
                isFavorite = false
            )
        )
    }
    return listMovie
}

