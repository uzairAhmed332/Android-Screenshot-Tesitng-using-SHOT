package com.example.myapplication

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

object TestHelper {
    val device: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

}