package com.horizonlabs.kotlindemo.utility

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.horizonlabs.kotlindemo.model.ProfileDetailsEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PreferenceManager {
    val context: Context
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "kotlin_demo"
    lateinit var gson: Gson
    private val mExecutor: ExecutorService
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    constructor(context: Context) {
        this.context = context
        this.mExecutor = Executors.newSingleThreadExecutor()
        this.gson = Gson()
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        this.editor = sharedPreferences.edit()
    }

    fun storeUser(profileDetailsEntity: ProfileDetailsEntity?) {

        mExecutor.execute {
            profileDetailsEntity?.let {
                sharedPreferences.edit().putString(Constants.USER_DETAILS, gson.toJson(profileDetailsEntity)).commit()
            }
        }
    }

    fun getUser(): String {
        return sharedPreferences.getString(Constants.USER_DETAILS, "")
    }


}