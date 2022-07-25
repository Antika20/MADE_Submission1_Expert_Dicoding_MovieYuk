package com.example.core.data


import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.domain.model.ModelMovie
import com.example.core.domain.repository.IMovie
import com.example.core.utils.DataMapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): IMovie {

    override fun getNowPlaying(): Flow<ApiResponse<List<ModelMovie>>> =
        remoteDataSource.getPlayingNow()


    override fun getFavoriteMovie(): Flow<List<ModelMovie>> =
        localDataSource.getAllAnyMovie().map {
            DataMapper.mapEntitiesMovieToDomain(it)
        }


    override suspend fun insertToFavoriteMovie(modelMovie: ModelMovie) {
        localDataSource.insertToFavoriteMovie(
            DataMapper.mapDomainToEntityMovie(modelMovie)
        )
    }

    override suspend fun deleteFromFavoriteMovie(modelMovie: ModelMovie) {
        localDataSource.deleteToFavorite(
            DataMapper.mapDomainToEntityMovie(modelMovie)
        )
    }

    override fun isFavorite(title:String): Flow<Boolean> =
        localDataSource.isFavoriteMovie(title)

    override fun searchMovie(query:String): Flow<ApiResponse<List<ModelMovie>>> =
       remoteDataSource.searchMovie(query)



}