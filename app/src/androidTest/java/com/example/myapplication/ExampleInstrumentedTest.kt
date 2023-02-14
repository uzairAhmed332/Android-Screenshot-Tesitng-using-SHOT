package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.karumi.shot.ScreenshotTest
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : ScreenshotTest {

    val device: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    // var activity: MainActivity? = null
    // var context: Context? = null
    //private val mActivity: Activity? = null
    private lateinit var activity: Activity

    @Before
    fun setup() {
      //  activity = getActivity()
        activity =  launchActivity()
//        val context  = ApplicationProvider.getApplicationContext<Context>()
//
//        val intent = Intent(Intent.ACTION_MAIN)
//        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
//        intent.component =
//            ComponentName("com.example.myapplication", "com.example.myapplication.MainActivity")
//         context.startActivity(intent)
//
//        device.wait(
//            Until.hasObject(By.pkg("com.example.myapplication").depth(0)),
//            5_000L
//        )

    }

    private fun getActivity(): Activity {
        // var activity: Activity? = null
        val inst = InstrumentationRegistry.getInstrumentation()
        val context = inst.context
        val monitor = inst.addMonitor("com.example.myapplication.MainActivity", null, false)
        val intent = context.packageManager.getLaunchIntentForPackage("com.example.myapplication")
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        //intent.component = ComponentName("com.example.myapplication", it)
        context.startActivity(intent)
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        activity = monitor.waitForActivityWithTimeout(3000)
        return activity
    }

    @Test
    fun activityDefaultTest() {
        compareScreenshot(activity)
    }

    @Test
    fun activityNavigationTest() {
        //  activity = getActivity()
        //  onView(withText("NEXT")).perform(click());
        if (device.wait(Until.hasObject(By.text("NEXT")), 2_000L)) {
            device.findObject(By.text("NEXT")).click()
        }

        Thread.sleep(2_000)
        TestHelper.device.wait(
            Until.hasObject(By.res("com.example.myapplication:id/edit_text")),
            2_000L
        )


      //  TestHelper.device.wait(Until.hasObject(By.text("PREVIOUS")), 2_000L)
        Thread.sleep(2_000)
        if (device.wait(Until.hasObject(By.res("com.example.myapplication:id/fab")), 2_000L)) {
            device.findObject(By.res("com.example.myapplication:id/fab")).click()
        }

        if (device.wait(
                Until.hasObject(By.res("com.example.myapplication:id/edit_text")),
                2_000L
            )
        ) {
            device.findObject(By.res("com.example.myapplication:id/edit_text")).text =
                "Taking a screenshot now!"
        }
        Thread.sleep(1_000)
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