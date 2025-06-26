package com.example.blankspace.network

import kotlinx.serialization.Serializable

@Serializable
data class Todo(val userId: Int,
                val id: Int,
                val title: String,
                val completed: Boolean
)