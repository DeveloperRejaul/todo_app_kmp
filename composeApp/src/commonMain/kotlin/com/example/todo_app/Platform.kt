package com.example.todo_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform