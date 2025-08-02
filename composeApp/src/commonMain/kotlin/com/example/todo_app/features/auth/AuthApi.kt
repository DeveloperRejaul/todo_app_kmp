package com.example.todo_app.features.auth

import com.example.todo_app.core.api.KTorInstance
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

object AuthApi {
    val clint = KTorInstance.authClient

    suspend fun login (name:String, pass:String):HttpResponse{
        val response: HttpResponse = clint.post("/auth/login") {
            setBody(LoginBody(username = name, password = pass,expiresInMins=1000))
        }
        return response
    }
}