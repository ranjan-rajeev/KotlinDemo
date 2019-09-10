package com.horizonlabs.kotlindemo.viewmodel

import android.content.SharedPreferences
import android.provider.SyncStateContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.horizonlabs.kotlindemo.adapters.ChatViewAdapter
import com.horizonlabs.kotlindemo.data.local.dao.ChatDao
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import com.horizonlabs.kotlindemo.data.remote.api.UserApi
import com.horizonlabs.kotlindemo.data.repository.ChatRepository
import com.horizonlabs.kotlindemo.data.repository.UserRepository
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.model.ProfileDetailsEntity
import com.horizonlabs.kotlindemo.model.QuestionEntity
import com.horizonlabs.kotlindemo.model.UserEntity
import com.horizonlabs.kotlindemo.utility.Constants
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

    fun addUserInput(input: String, chatType: Int = ChatViewAdapter.TYPE_SENT, isUserInputRequired: Boolean = false) {
        chatRepository.addUserInput(input, chatType, isUserInputRequired)
    }

    fun fetchNextChat(seqId: Int) {
        chatRepository.fetchNextChat(seqId)
    }

    fun updateChatEntity(chatEntity: ChatEntity, questionEntity: QuestionEntity) {
        chatRepository.updateChatEntity(chatEntity, questionEntity)
    }


}