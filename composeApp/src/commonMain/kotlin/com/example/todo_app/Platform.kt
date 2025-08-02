package com.example.todo_app

import com.liftric.kvault.KVault

//import com.example.todo_app.core.database.AppDatabase


interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun log(message: String)

//expect fun getDatabase(): AppDatabase

expect fun getDb(): KVault

