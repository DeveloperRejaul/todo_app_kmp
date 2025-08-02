package com.example.todo_app.features.home

import kotlinx.serialization.Serializable


@Serializable
data class HomeModal(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

@Serializable
data class CreatePostModal (
    val body: String,
    val title: String,
    val userId: Int
)