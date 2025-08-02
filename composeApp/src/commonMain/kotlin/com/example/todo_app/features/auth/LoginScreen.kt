package com.example.todo_app.features.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.todo_app.core.api.NetworkResponse
import com.example.todo_app.core.components.Button
import com.example.todo_app.core.components.Container
import com.example.todo_app.core.components.Input
import com.example.todo_app.core.constance.InputVariant
import com.example.todo_app.core.theme.Typography

object  LoginScreen: Screen {
    @Composable
    override fun Content() {
        val authViewModal = viewModel { AuthViewModal() }
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val loginResult = authViewModal.loginResult.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        Container(
            headerShow = false,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Welcome TO TODO App", style = Typography.titleLarge.copy(fontSize = 30.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(height = 50.dp))
            Input(
                value = userName,
                onChange = { userName = it},
                placeholder = "Type your user name",
                label = "User name",
            )
            Spacer(modifier = Modifier.height(height = 10.dp))
            Input(
                value = password,
                onChange = { password = it},
                placeholder = "Type your password",
                label = "Password",
                variant = InputVariant.PASSWORD,
                keyboardAction = ImeAction.Done
            )
            Spacer(modifier = Modifier.height(height = 10.dp))
            Button(
                text = "Login",
                onClick = {
                    authViewModal.login(userName, password)
                },
                isLoading = loginResult.value === NetworkResponse.Loading
            )
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("You don't have an account? ", style = Typography.bodyMedium)
                TextButton(
                    onClick = {
                        navigator.push(RegisterScreen)
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("Register")
                }
            }
        }
    }
}
