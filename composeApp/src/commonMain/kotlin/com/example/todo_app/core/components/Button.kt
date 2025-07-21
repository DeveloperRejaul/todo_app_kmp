package com.example.todo_app.core.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todo_app.core.theme.Typography

@Composable
fun Button (
    modifier: Modifier = Modifier,
    text:String,
    onClick: () -> Unit,
    isLoading: Boolean=false,
    bg: Color = Color.Blue
) {
    Button(
        enabled = !isLoading,
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(60.dp),
        contentPadding = PaddingValues(vertical = 10.dp),
        // shape = RectangleShape
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (!isLoading) bg else bg.copy(alpha = 0.5f),
            contentColor = Color.White,
            disabledContainerColor = bg.copy(alpha = 0.5f),
            disabledContentColor = Color.White.copy(alpha = 0.7f)
        )

    ) {
        if(isLoading == true) CircularProgressIndicator(
            modifier = Modifier.width(20.dp).height(20.dp),
            color = MaterialTheme.colorScheme.surface,
            trackColor = MaterialTheme.colorScheme.primary,
        ) else  Text(text, style = Typography.titleMedium)

    }
}