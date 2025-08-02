package com.example.todo_app

import android.os.Build
import android.util.Log
import com.liftric.kvault.KVault

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


actual fun log(message: String) {
    Log.d("KMP_LOG", message)
}

actual fun getDb(): KVault {
    val store = KVault(ApplicationHolder.context, "user")
    return store
}