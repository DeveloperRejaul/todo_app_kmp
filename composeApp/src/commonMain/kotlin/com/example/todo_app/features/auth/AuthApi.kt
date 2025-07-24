package com.example.todo_app.features.auth

import com.example.todo_app.core.api.KTorInstance
import io.ktor.client.call.body
import io.ktor.client.request.get

object AuthApi {
    val clint = KTorInstance.client

    suspend fun login ():List<AuthModal> {
        val response = clint.get("/posts")
        val  result: List<AuthModal> = response.body()
        return result
    }
}