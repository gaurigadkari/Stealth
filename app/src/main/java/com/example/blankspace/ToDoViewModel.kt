package com.example.blankspace

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _todoTitle = mutableStateOf<String?>(null)
    val todoTitle: State<String?> = _todoTitle

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun getTodoById(id: Int) {
        viewModelScope.launch {
            try {
                val todo = repository.fetchTodo(id)
                _todoTitle.value = todo.title
                _errorMessage.value = null
            } catch (e: Exception) {
                _todoTitle.value = null
                _errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}