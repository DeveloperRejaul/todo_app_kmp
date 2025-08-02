package com.example.todo_app.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_app.core.theme.Typography
import com.example.todo_app.features.home.HomeModal


@Composable
fun TodoItem (todo: HomeModal, onEdit: () -> Unit = {}, onDelete: () -> Unit = {}  ) {
    val isLoading = remember { mutableStateOf<Boolean>(false) }


    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "ID: ${todo.id.toString()}",
                    style = Typography.titleSmall
                )
                Text(
                    text = todo.title,
                    style = Typography.titleLarge.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = todo.body,
                    style = Typography.bodyLarge.copy(fontSize = 14.sp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    text = "Delete",
                    onClick = {
                        isLoading.value = true;
                        onDelete()
                    },
                    modifier = Modifier
                        .width(80.dp)
                        .height(50.dp),
                    bg = MaterialTheme.colorScheme.error,
                    isLoading = isLoading.value
                )
                Button(
                    text = "Edit" ,
                    onClick = onEdit,
                    modifier = Modifier
                        .width(80.dp)
                        .height(50.dp)
                )
            }
        }
    }
}