package com.example.todo_app.features.splash

import kotlinx.serialization.Serializable


@Serializable
data class SplashModal(
    var id: Int,
    var username:String,
    var email:String,
    var firstName:String,
    var lastName:String,
    var gender:String,
    var image:String
)