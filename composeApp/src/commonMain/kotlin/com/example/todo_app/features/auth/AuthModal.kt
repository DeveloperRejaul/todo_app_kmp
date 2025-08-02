package com.example.todo_app.features.auth

import kotlinx.serialization.Serializable


@Serializable
data class AuthModal(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)


@Serializable
data class LoginBody (var username:String, val password: String, val expiresInMins: Int)

@Serializable
data class AuthLoginModal (
    val id: Int,
    val accessToken: String,
    val refreshToken: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
)
