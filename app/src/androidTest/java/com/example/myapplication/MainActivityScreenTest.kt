package com.example.myapplication

import android.util.Log
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.karumi.shot.ScreenshotTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityScreenTest : ScreenshotTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun activityTest() {
        val activity = launchActivity()

        compareScreenshot(activity)
    }

    // Hack needed until we fully support Activity Scenarios
    private fun launchActivity(): MainActivity {
        var activity: MainActivity? = null
        activityScenarioRule.scenario.onActivity {
            activity = it
        }
        while (activity == null) {
            Log.d("MainActivityTest", "Waiting for activity to be initialized")
        }
        return activity!!
    }
}