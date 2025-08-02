package com.example.todo_app.features.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.todo_app.core.api.NetworkResponse
import com.example.todo_app.core.components.Button
import com.example.todo_app.core.components.Container
import com.example.todo_app.core.components.Input
import org.koin.compose.koinInject

data class CreateTaskScreen (
    val id:Int = 0,
    val userID: Int = 0,
    val title: String="",
    val body: String=""
): Screen {

    @Composable
    override fun Content() {
        val viewModel: HomeViewModal = koinInject()
        val action = viewModel.isLoading.collectAsState()

        var titleState by remember { mutableStateOf<String>(title) }
        var bodyState by remember { mutableStateOf<String>( body) }
        val navigator = LocalNavigator.currentOrThrow

        val isUpdate = !(id == 0 && userID == 0 && title =="" && body=="")

        LaunchedEffect(Unit) {
            viewModel.event.collect { num ->

               if(num == 1) navigator.pop()
            }
        }


        Scaffold { innerPadding ->
            Container(
                modifier = Modifier.padding(innerPadding),
                headerLabel = if(isUpdate) "Update Task" else "Create Task",
                onBack = {navigator.pop()}
            ) {
                Input(
                    value = titleState,
                    onChange = { titleState = it},
                    placeholder = "Type your title",
                    label = "Title",
                    singleLine = false
                )
                Input(
                    value = bodyState,
                    onChange = {bodyState = it},
                    placeholder = "Type your body",
                    label = "Body",
                    keyboardAction = ImeAction.Done,
                    singleLine = false
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    isLoading = action.value === NetworkResponse.Loading,
                    text =  if(isUpdate)"Update" else "Create",
                    onClick ={
                        if (isUpdate) {
                            viewModel.update(HomeModal(
                                id = id,
                                userId = userID,
                                title = titleState,
                                body = bodyState
                            ))
                        }
                        else {
                            viewModel.create(CreatePostModal(body=bodyState, title=titleState, userId = 1))
                        }
                    }
                )
            }
        }
    }
}



