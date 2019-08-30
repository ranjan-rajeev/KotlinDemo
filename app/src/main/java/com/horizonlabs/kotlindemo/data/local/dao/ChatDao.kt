package com.horizonlabs.kotlindemo.data.local.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.model.UserEntity


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Dao
interface ChatDao {
    @Query("SELECT * FROM ChatEntity")
    fun getAllChat(): LiveData<List<ChatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChatList(userEntities: List<ChatEntity>?)

    @Insert()
    fun insertChat(chatEntity: ChatEntity)

    @Update
    fun updateChat(chatEntity: ChatEntity)

    @Delete
    fun deleteChat(chatEntity: ChatEntity)


    @Query("SELECT max(seqId) FROM ChatEntity")
    fun getMaxSeq(): Int
}