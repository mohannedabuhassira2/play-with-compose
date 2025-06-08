package com.example.playwithcompose.data.networking

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Failed(val exception: Exception) : ApiResult<Nothing>()
    object Progress: ApiResult<Nothing>()
}
