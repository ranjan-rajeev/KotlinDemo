package com.horizonlabs.kotlindemo.data.local.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.horizonlabs.kotlindemo.model.UserEntity


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity")
    fun getUsersSync(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(userEntities: List<UserEntity>?)

    @Update
    fun updateUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)
}