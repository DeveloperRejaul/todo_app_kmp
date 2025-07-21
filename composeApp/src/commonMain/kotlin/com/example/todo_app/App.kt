package com.example.todo_app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todo_app.core.components.Input
import com.example.todo_app.features.auth.Login
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun App() {
    MaterialTheme {
        Login()
//        Scaffold { innerPadding ->
//            Box(
//                modifier = Modifier.padding(innerPadding).fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ){
//                Input(
//                    onChange = {},
//                    value = "sdadf",
//                    placeholder = "TODO()",
//                    label = "TODO()",
//                )
//            }
//        }
    }
}

@Composable
@Preview
fun PApp () {
    App()
}