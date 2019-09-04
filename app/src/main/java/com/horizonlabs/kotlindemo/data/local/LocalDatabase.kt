package com.horizonlabs.kotlindemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.horizonlabs.kotlindemo.data.local.converter.StringConverter
import com.horizonlabs.kotlindemo.data.local.dao.ChatDao
import com.horizonlabs.kotlindemo.data.local.dao.ExamDao
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.model.ExamEntity
import com.horizonlabs.kotlindemo.model.UserEntity


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Database(entities = [UserEntity::class, ChatEntity::class, ExamEntity::class], exportSchema = false, version = 3)
@TypeConverters(StringConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getChatDao(): ChatDao
    abstract fun getExamDao(): ExamDao
}