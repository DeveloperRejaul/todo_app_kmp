package com.example.todo_app

import platform.UIKit.UIDevice
import platform.Foundation.NSLog

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun log(message: String) {
    NSLog(message)
}