package com.example.todo_app.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.todo_app.core.constance.InputVariant
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Eye
import compose.icons.fontawesomeicons.regular.EyeSlash

@Composable
fun Input (
    value: String,
    onChange:(it:String)-> Unit,
    placeholder:String,
    label:String,
    variant: InputVariant = InputVariant.TEXT,
    keyboardAction:ImeAction = ImeAction.Next,
    singleLine: Boolean = true
) {
    var isVisible by remember { mutableStateOf(false) }
    val visualTransformation = when (variant) {
        InputVariant.PASSWORD ->
            if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
        InputVariant.TEXT ->
            VisualTransformation.None
    }

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder) },
        label = {Text(label) },
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(imeAction = keyboardAction),
        visualTransformation = visualTransformation, // for password hide and show
        trailingIcon = {
            if(variant === InputVariant.PASSWORD){
                val image = if (isVisible) FontAwesomeIcons.Regular.Eye else FontAwesomeIcons.Regular.EyeSlash;
                val description = if(isVisible) "Hide password" else "Show password";
                IconButton(onClick = {
                    isVisible = !isVisible
                }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        }
    )
}