package com.example.todo_app.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app.core.api.NetworkResponse
import com.example.todo_app.core.constance.DbKeys
import com.example.todo_app.getDb
import com.example.todo_app.log
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModal : ViewModel() {
    private  val authApi = AuthApi
    private  val db = getDb()

    private val  _loginResult = MutableStateFlow<NetworkResponse<AuthLoginModal>>(NetworkResponse.Initial)
    val loginResult: StateFlow<NetworkResponse<AuthLoginModal>> = _loginResult;


    fun login (name:String, pass:String) {
        _loginResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {

               val res = authApi.login(name, pass)
               if(res.status == HttpStatusCode.OK){
                  val result = res.body<AuthLoginModal>()
                   _loginResult.value = NetworkResponse.Success(result)

                   // save token to db
                   db.set(key = DbKeys.USER_ACCESS_TOKEN, stringValue = result.accessToken)
               }
            }catch (e: Exception) {
                log(e.toString())
                _loginResult.value = NetworkResponse.Error("Something went wrong")
            }
        }
    }

    fun getStoreData () {
        viewModelScope.launch {
            val data = db.string(DbKeys.USER_ACCESS_TOKEN)

            log(data.toString())
        }
    }
}