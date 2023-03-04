package com.rbths.newstopheadlines

import android.os.RemoteException
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DisableAnimationsRule : TestRule {

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    disableAnimations()
                    base?.evaluate()
                } finally {
                    enableAnimations()
                }
            }
        }
    }

    private fun disableAnimations() {
        try {
            UiDevice.getInstance(getInstrumentation())
                .executeShellCommand("settings put global window_animation_scale 0.0")
            UiDevice.getInstance(getInstrumentation())
                .executeShellCommand("settings put global transition_animation_scale 0.0")
            UiDevice.getInstance(getInstrumentation())
                .executeShellCommand("settings put global animator_duration_scale 0.0")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    private fun enableAnimations() {
        try {
            UiDevice.getInstance(getInstrumentation())
                .executeShellCommand("settings put global window_animation_scale 1.0")
            UiDevice.getInstance(getInstrumentation())
                .executeShellCommand("settings put global transition_animation_scale 1.0")
            UiDevice.getInstance(getInstrumentation())
                .executeShellCommand("settings put global animator_duration_scale 1.0")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
}