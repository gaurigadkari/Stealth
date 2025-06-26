package com.example.blankspace

import com.example.blankspace.network.ApiService
import com.example.blankspace.network.Todo

class TodoRepository(private val apiService: ApiService) {
    suspend fun fetchTodo(id: Int): Todo {
        return apiService.getTodo(id)
    }
}