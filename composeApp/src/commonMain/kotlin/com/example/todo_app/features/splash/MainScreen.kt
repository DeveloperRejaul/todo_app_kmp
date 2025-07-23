package com.example.todo_app.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import com.example.todo_app.core.components.ScreenCenterLoading


class MainScreen () : Screen {
    @Composable
    override fun Content() {
        LaunchedEffect(Unit) {

        }

        ScreenCenterLoading()
    }
}