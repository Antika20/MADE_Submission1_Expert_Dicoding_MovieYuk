package com.example.core.data.source.remote.network

sealed class ApiResponse<T> {
    class Loading<T>: ApiResponse<T>()
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(val errorMessage: String) : ApiResponse<T>()
    class Empty<T> : ApiResponse<T>()

    companion object{
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(message: String) = Error<T>(message)
        fun <T> empty() = Empty<T>()
    }
}