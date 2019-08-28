package com.horizonlabs.kotlindemo.di.module

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.horizonlabs.kotlindemo.data.local.LocalDatabase
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import com.horizonlabs.kotlindemo.model.ProfileDetailsEntity
import com.horizonlabs.kotlindemo.utility.Constants
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Rajeev Ranjan -  ABPB on 22-08-2019.
 */
@Module
class SharedPreferenceModule {

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "kotlin_demo"

    @Provides
    @Singleton
    internal fun provideSharedPreference(application: Application): SharedPreferences {
        return application.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    @Provides
    @Singleton
    internal fun provideEdit(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    @Provides
    @Singleton
    internal fun provideUser(sharedPreferences: SharedPreferences, gson: Gson): ProfileDetailsEntity {


        var str = sharedPreferences.getString(Constants.USER_DETAILS, "")
        if (!str.equals("")) {
            return gson.fromJson(
                sharedPreferences.getString(Constants.USER_DETAILS, ""),
                ProfileDetailsEntity::class.java
            )
        }
        return ProfileDetailsEntity()
    }
}