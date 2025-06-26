package com.example.blankspace.network

import com.example.blankspace.network.Todo
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") todoId: Int): Todo
}