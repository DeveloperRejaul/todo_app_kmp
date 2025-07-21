package com.example.todo_app.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Container(
    modifier: Modifier = Modifier,
    headerLabel: String = "",
    headerShow: Boolean = true,
    onBack:() -> Unit = { },
    showBackIcon: Boolean = true,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.background(MaterialTheme.colorScheme.surface).padding(horizontal = 10.dp).fillMaxSize()) {
        if(headerShow) Header(onBack, headerLabel, showBackIcon)
        Column(
            modifier = Modifier.fillMaxSize().weight(1f).imePadding(),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }
    }
}


