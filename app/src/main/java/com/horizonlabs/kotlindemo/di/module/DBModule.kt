package com.horizonlabs.kotlindemo.di.module

import android.app.Application
import androidx.room.Room
import com.google.firebase.database.FirebaseDatabase
import com.horizonlabs.kotlindemo.data.local.LocalDatabase
import com.horizonlabs.kotlindemo.data.local.dao.ChatDao
import com.horizonlabs.kotlindemo.data.local.dao.ExamDao
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Module
class DBModule {

    /*
         * The method returns the Database object
         * */
    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): LocalDatabase {

        return Room.databaseBuilder(
            application,
            LocalDatabase::class.java, "_database"
        )
            .fallbackToDestructiveMigration()
            .build();
    }

    @Provides
    @Singleton
    internal fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance();
    }

    /*
     * We need the MovieDao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */
    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: LocalDatabase): UserDao {
        return appDatabase.getUserDao()
    }

    @Provides
    @Singleton
    internal fun provideChatDao(appDatabase: LocalDatabase): ChatDao {
        return appDatabase.getChatDao()
    }

    @Provides
    @Singleton
    internal fun provideExamDao(appDatabase: LocalDatabase): ExamDao {
        return appDatabase.getExamDao()
    }
}