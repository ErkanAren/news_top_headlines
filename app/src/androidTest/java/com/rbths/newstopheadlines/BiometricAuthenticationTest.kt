package com.rbths.newstopheadlines

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BiometricAuthenticationTest {

    private var mContext: Context? = null
    private var mBiometricManager: BiometricManager? = null

    @Before
    fun setUp() {
        mContext = InstrumentationRegistry.getInstrumentation().context
        mBiometricManager = (mContext as Context).getSystemService(BiometricManager::class.java)
    }


    @Test
    fun test_canAuthenticate() {
        assertNotEquals(
            "Device should not have any biometrics enrolled",
            mBiometricManager?.canAuthenticate(BIOMETRIC_WEAK), BiometricManager.BIOMETRIC_SUCCESS
        )
        assertNotEquals(
            "Device should not have any biometrics enrolled",
            mBiometricManager?.canAuthenticate(DEVICE_CREDENTIAL or BIOMETRIC_WEAK),
            BiometricManager.BIOMETRIC_SUCCESS
        )
    }
}