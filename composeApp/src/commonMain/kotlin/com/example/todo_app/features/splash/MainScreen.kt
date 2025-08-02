package com.example.todo_app.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.todo_app.core.api.NetworkResponse
import com.example.todo_app.core.components.ScreenCenterLoading
import com.example.todo_app.features.auth.LoginScreen
import com.example.todo_app.features.home.HomeScreen


object MainScreen : Screen {
    @Composable
    override fun Content() {
        val splashViewModal = viewModel { SplashViewModal() }
        val authResult = splashViewModal.result.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            splashViewModal.auth();
        }

        when(authResult.value) {
            is NetworkResponse.Error -> {
               navigator.replace(LoginScreen)
            }
            NetworkResponse.Loading -> {
                ScreenCenterLoading()
            }
            is NetworkResponse.Success<*> -> {
                navigator.replace(HomeScreen)
            }
            else -> Unit
        }
    }
}