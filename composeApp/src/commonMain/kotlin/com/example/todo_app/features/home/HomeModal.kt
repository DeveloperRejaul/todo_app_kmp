package com.example.todo_app.features.home

data class HomeModal(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

data class CreatePostModal (
    val body: String,
    val title: String,
    val userId: Int
)