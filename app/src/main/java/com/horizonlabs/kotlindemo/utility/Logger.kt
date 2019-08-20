package com.horizonlabs.kotlindemo.utility

import android.util.Log
import com.horizonlabs.kotlindemo.BuildConfig


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class Logger {
    companion object {
        private val TAG = "LOGGER"
        private var DEBUG_ENABLED = BuildConfig.DEBUG
        fun v(msg: String) {
            if (isDebugEnabled()) {
                Log.v(TAG, msg)
            }

        }

        fun v(tag: String, msg: String) {
            if (isDebugEnabled()) {
                Log.v(tag, msg)
            }

        }

        fun d(msg: String) {
            if (isDebugEnabled()) {
                Log.d(TAG, msg)
            }

        }

        fun d(tag: String, msg: String) {
            if (isDebugEnabled()) {
                Log.d(tag, msg)
            }

        }

        fun i(msg: String) {
            if (isDebugEnabled()) {
                Log.i(TAG, msg)
            }

        }

        fun i(tag: String, msg: String) {
            if (isDebugEnabled()) {
                Log.i(tag, msg)
            }

        }

        fun w(msg: String) {
            if (isDebugEnabled()) {
                Log.w(TAG, msg)
            }

        }

        fun w(tag: String, msg: String) {
            if (isDebugEnabled()) {
                Log.w(tag, msg)
            }

        }

        fun e(msg: String) {

            if (isDebugEnabled()) {
                Log.e(TAG, msg)
            }

        }

        fun e(tag: String, msg: String) {
            if (isDebugEnabled()) {
                Log.e(tag, msg)
            }

        }

        fun isDebugEnabled(): Boolean {
            return DEBUG_ENABLED
        }

        fun setLogStatus(value: Boolean) {
            DEBUG_ENABLED = value
        }
    }
}