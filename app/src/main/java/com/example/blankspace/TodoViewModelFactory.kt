package com.example.blankspace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.blankspace.network.ApiClient

class TodoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = TodoRepository(ApiClient.apiService)
        return TodoViewModel(repository) as T
    }
}