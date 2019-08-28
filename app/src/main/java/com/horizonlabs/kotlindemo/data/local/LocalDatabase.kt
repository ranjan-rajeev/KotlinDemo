package com.horizonlabs.kotlindemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.horizonlabs.kotlindemo.data.local.dao.ChatDao
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.model.UserEntity


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Database(entities = [UserEntity::class,ChatEntity::class], exportSchema = false, version = 2)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getChatDao(): ChatDao
}