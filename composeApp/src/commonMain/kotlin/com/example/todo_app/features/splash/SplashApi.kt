package com.example.todo_app.features.splash

import com.example.todo_app.core.api.KTorInstance
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse

object SplashApi {
    val clint = KTorInstance.authClient

    suspend fun auth (token: String):HttpResponse{
        val response: HttpResponse = clint.get("/auth/me") {
            headers {
                append("Authorization", "Bearer $token")
            }
        }
        return response
    }
}