package com.example.todo_app.features.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.todo_app.core.components.Container
import com.example.todo_app.core.components.Input
import com.example.todo_app.core.constance.InputVariant
import com.example.todo_app.core.theme.Typography
import com.example.todo_app.core.components.Button



class RegisterScreen(): Screen {
    @Composable
    override fun Content() {
        var userName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
         val  navigator = LocalNavigator.currentOrThrow
        // val response = viewModal.registerResult.observeAsState()

        Scaffold { innerPadding ->
            Container(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(innerPadding),
                headerLabel = "Register",
                onBack = {
                    navigator.pop();
                }
            ){
                Spacer(modifier = Modifier.height(height = 100.dp))
                Text("Welcome TO TODO App", style = Typography.titleLarge.copy(fontSize = 30.sp, fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(height = 50.dp))
                Input(
                    value = userName,
                    placeholder = "Type your user name",
                    label = "User name",
                    onChange = {userName = it}
                )
                Spacer(modifier = Modifier.height(height = 10.dp))
                Input(
                    value = email,
                    placeholder = "Type your user email",
                    label = "Email",
                    onChange = {email = it}
                )
                Spacer(modifier = Modifier.height(height = 10.dp))
                Input(
                    value = password,
                    placeholder = "Type Password",
                    label = "Password",
                    onChange = {password = it},
                    variant =  InputVariant.PASSWORD
                )
                Spacer(modifier = Modifier.height(height = 10.dp))
                Input(
                    value = confirmPassword,
                    placeholder = "Type password again",
                    label = "Confirm password",
                    onChange = {confirmPassword = it},
                    variant =  InputVariant.PASSWORD,
                    keyboardAction = ImeAction.Done ,
                )
                Spacer(modifier = Modifier.height(height = 10.dp))
                Button(
                    text = "Register",
                    onClick = {
                        if(email.isEmpty() || userName.isEmpty()) {
                            // showToast(context, "Name or email is empty")
                            return@Button
                        }
                        // viewModal.register(RegisterModal(age = 20, firstName = userName , lastName = email))
                    },
                    // isLoading = response.value === NetworkResponse.Loading
                )
            }
        }

    }
}