package com.example.core.data.source.local.entitity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="movieId")
    val movieId:Int,

    @ColumnInfo(name="title")
    val title:String,

    @ColumnInfo(name="release_date")
    val release_date:String,

    @ColumnInfo(name="poster_path")
    val poster_path:String,

    @ColumnInfo(name="overview")
    val overview:String,

    @ColumnInfo(name="popularity")
    val popularity:Double,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable

