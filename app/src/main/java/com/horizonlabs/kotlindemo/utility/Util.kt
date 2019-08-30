package com.horizonlabs.kotlindemo.utility

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat.getSystemService
import android.telephony.TelephonyManager
import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class Util {
    companion object {
        fun getAndroidId(context: Context): String {
            return Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.ANDROID_ID
            );
        }

        fun getCurrentTime(): String {
            var answer: String = ""

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
                answer = current.format(formatter)
            } else {
                var date = Date();
                date.time = Calendar.getInstance().timeInMillis
                val formatter = SimpleDateFormat("dd MMM yyyy HH:mma")
                answer = formatter.format(date)
            }
            return answer
        }

    }
}