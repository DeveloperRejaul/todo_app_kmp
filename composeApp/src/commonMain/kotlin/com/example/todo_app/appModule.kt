package com.example.todo_app

import com.example.todo_app.features.home.HomeViewModal
import org.koin.dsl.module
val appModule = module {
    single { HomeViewModal() }
}