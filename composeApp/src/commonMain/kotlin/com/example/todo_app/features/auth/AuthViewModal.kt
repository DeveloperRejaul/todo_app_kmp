package com.example.todo_app.features.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app.log
import kotlinx.coroutines.launch

class AuthViewModal : ViewModel() {

    private  val authApi = AuthApi
    var result by mutableStateOf("hello")
        private set


    fun login () {
        try {
            viewModelScope.launch {
               val res = authApi.login()

                for (x in res) {
                    log(x.id.toString())
                }
            }
        }catch (e: Exception) {
            log(e.toString())
        }
    }
}