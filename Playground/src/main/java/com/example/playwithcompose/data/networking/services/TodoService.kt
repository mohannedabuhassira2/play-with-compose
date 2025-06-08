package com.example.playwithcompose.data.networking.services

import com.example.playwithcompose.data.networking.model.Todo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoService {
    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: Long): Response<Todo>
}
