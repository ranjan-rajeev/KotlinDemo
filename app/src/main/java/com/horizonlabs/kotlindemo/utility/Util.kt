package com.horizonlabs.kotlindemo.utility

import android.content.Context
import android.provider.Settings
import androidx.core.content.ContextCompat.getSystemService
import android.telephony.TelephonyManager


class Util {
    companion object {
        fun getAndroidId(context: Context): String {
            return Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.ANDROID_ID
            );
        }

    }
}