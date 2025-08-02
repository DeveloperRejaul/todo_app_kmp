package com.example.todo_app.features.splash

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

class SplashViewModal : ViewModel() {

    private val db = getDb()
    private val splashApi = SplashApi

    private var _result = MutableStateFlow<NetworkResponse<SplashModal>>(NetworkResponse.Initial)
    val result : StateFlow<NetworkResponse<SplashModal>> = _result


    fun  auth () {
        viewModelScope.launch {
            _result.value = NetworkResponse.Loading
            try {
                val token = db.string(DbKeys.USER_ACCESS_TOKEN);
                val res =  splashApi.auth(token.toString())

                if(res.status == HttpStatusCode.OK) {
                    val data = res.body<SplashModal>()
                    _result.value = NetworkResponse.Success(data)
                }else {
                    _result.value=  NetworkResponse.Error("Something went wrong")
                }
            }catch (e: Exception) {
                log(e.toString())
                _result.value = NetworkResponse.Error("Something went wrong")
            }

        }
    }
}