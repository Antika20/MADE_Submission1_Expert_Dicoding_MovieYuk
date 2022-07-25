package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.domain.model.ModelMovie
import com.example.core.domain.model.toListMovie

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    companion object {
        const val TAG = "RemoteDataSource"
    }

    fun getPlayingNow() = flow<ApiResponse<List<ModelMovie>>> {
        emit(ApiResponse.loading())
        val serviceData = apiService.getPlaying()
        serviceData.let {
            if (it.isSuccessful && it.body() != null){
                emit(ApiResponse.success(it.body()?.results?.toListMovie()?: listOf()))
            }else{
                emit(ApiResponse.failed(it.message().toString()))
            }
        }
    }.catch {
        Log.d(TAG,"getPlayingNow:${it.message}")
        emit(ApiResponse.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun searchMovie(query: String)= flow<ApiResponse<List<ModelMovie>>> {
        emit(ApiResponse.loading())
        val serviceSearch = apiService.searchMovie(query =query)
        serviceSearch.let {
            if (it.isSuccessful && it.body() != null){
            val dataMovie = it.body()?.results?.toListMovie()
                if (dataMovie?.isEmpty() == true){
                    emit(ApiResponse.empty())
                }else{
                    emit(ApiResponse.success(dataMovie?: listOf()))
                }
            }else{
                emit(ApiResponse.failed(it.message().toString()))
            }
        }
    }.catch {
        Log.d(TAG,"searchMovie:${it.message.toString()}")
        emit(ApiResponse.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}













