package com.example.myapplication

import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Until
import com.example.myapplication.TestHelper.device
import java.sql.Time

interface IScreenObject {
    val IDENTIFIER: BySelector

    fun waitForScreen(timeout: Long = 5_000L): Boolean {
        return device.wait(Until.hasObject(IDENTIFIER), timeout)
    }

    fun <S : IScreenObject> pressBack(expectedScreen: S): S {
        device.pressBack()
        return expectedScreen
    }
}