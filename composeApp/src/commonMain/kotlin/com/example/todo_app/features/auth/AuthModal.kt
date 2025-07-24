package com.example.todo_app.features.auth

import kotlinx.serialization.Serializable


@Serializable
data class AuthModal(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)