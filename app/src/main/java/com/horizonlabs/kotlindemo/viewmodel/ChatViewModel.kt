package com.horizonlabs.kotlindemo.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.horizonlabs.kotlindemo.data.local.dao.ChatDao
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import com.horizonlabs.kotlindemo.data.remote.api.UserApi
import com.horizonlabs.kotlindemo.data.repository.ChatRepository
import com.horizonlabs.kotlindemo.data.repository.UserRepository
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.model.UserEntity
import javax.inject.Inject


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class ChatViewModel @Inject constructor(
    chatDao: ChatDao,
    sharedPreferences: SharedPreferences,
    database: FirebaseDatabase
) :
    ViewModel() {

    private val chatRepository = ChatRepository(chatDao, sharedPreferences, database)

    fun getChatList(): LiveData<List<ChatEntity>> {
        return chatRepository.getChatList();
    }


}