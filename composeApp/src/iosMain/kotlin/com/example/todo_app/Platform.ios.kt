package com.example.todo_app

import com.liftric.kvault.KVault
import platform.Foundation.NSLog
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun log(message: String) {
    NSLog(message)
}

actual fun getDb(): KVault {
    val store = KVault()
    return store
}