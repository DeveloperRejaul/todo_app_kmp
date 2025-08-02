package com.example.todo_app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.todo_app.features.splash.MainScreen
import org.koin.compose.KoinApplication


@Composable
    fun App() {
        MaterialTheme {
            KoinApplication(application = {
                modules(appModule)
            }){
                Navigator(MainScreen) { navigator ->
                    SlideTransition(navigator)
                }
            }
        }
    }

