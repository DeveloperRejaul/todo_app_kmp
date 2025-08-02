package com.example.todo_app.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.todo_app.core.api.NetworkResponse
import com.example.todo_app.core.components.Container
import com.example.todo_app.core.components.ListView
import com.example.todo_app.core.components.ScreenCenterLoading
import com.example.todo_app.core.components.TodoItem
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Plus
import org.koin.compose.koinInject

var page = 1;
object HomeScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: HomeViewModal = koinInject()

        val posts = viewModel.posts.collectAsState()
        val moreFetching = viewModel.moreFetching.collectAsState()
        val isRefreshing = viewModel.isRefreshing.collectAsState()
        // load fast file 10 item
        LaunchedEffect(Unit) {
            if(posts.value == NetworkResponse.Initial) {
                viewModel.getPosts()
            }
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navigator.push(CreateTaskScreen())
                    }
                ) {
                    Icon(
                        FontAwesomeIcons.Solid.Plus,
                        "Small floating action button.",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        ) { innerPadding ->
            Container(modifier = Modifier.padding(innerPadding), headerLabel = "TODO App", showBackIcon = false) {

                when(posts.value) {
                    is NetworkResponse.Error -> {
                        Text((posts.value as NetworkResponse.Error).message)
                    }
                    is NetworkResponse.Loading -> {
                        ScreenCenterLoading()
                    }
                    is NetworkResponse.Success<List<HomeModal>> -> {
                        val todoList = (posts.value as NetworkResponse.Success<List<HomeModal>>).data
                        ListView(
                            onRefresh = {
                                page = 1;
                                viewModel.getPosts(refreshing = true)
                            },
                            isRefreshing = isRefreshing.value === NetworkResponse.Loading,
                            items = todoList,
                            itemKey = {item -> item.id},
                            loadMore = {
                                page += 1;
                                viewModel.getPostByPage(page,10)
                            },
                            itemContent = { todo ->
                                TodoItem(
                                    todo,
                                    onEdit = {
                                        navigator.push(CreateTaskScreen(
                                            id = todo.id,
                                            userID = todo.userId,
                                            title = todo.title,
                                            body = todo.body
                                        ))
                                    },
                                    onDelete = {
                                        viewModel.remove(todo.id)
                                    },
                                )
                            },
                            isLoadingMore = moreFetching.value === NetworkResponse.Loading,
                            loadingItem = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.width(20.dp),
                                        color = MaterialTheme.colorScheme.secondary,
                                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                    )
                                }
                            }
                        )
                    }
                    else -> Unit
                }
            }
        }
    }
}