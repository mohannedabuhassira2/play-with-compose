package com.example.playwithcompose.domain

import com.example.playwithcompose.data.networking.ApiResult
import com.example.playwithcompose.data.networking.services.TodoService
import com.example.playwithcompose.data.networking.model.Todo
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class Repository(
    private val todoApi: TodoService
) {
    suspend fun getTodo(id: Long): ApiResult<Todo> {
        return safeApiCall { todoApi.getTodo(id) }
    }

    private suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): ApiResult<T> {
        return try {
            val response = apiCall()
            if (response.body() == null) {
                ApiResult.Failed(Exception("Response body is null"))
            }
            ApiResult.Success(response.body()!!)
        } catch (e: HttpException) {
            ApiResult.Failed(e)
        } catch (e: IOException) {
            ApiResult.Failed(e)
        }
    }
}
