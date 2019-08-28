package com.horizonlabs.kotlindemo.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.horizonlabs.kotlindemo.data.local.dao.ChatDao
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.model.ProfileDetailsEntity
import com.horizonlabs.kotlindemo.utility.Constants
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Singleton
class ChatRepository(
    val chatDao: ChatDao,
    val sharedPreferences: SharedPreferences,
    val database: FirebaseDatabase,
    var profileDetailsEntity: ProfileDetailsEntity
) {

    val dbChat = database.getReference("chat")
    private val mExecutor = Executors.newSingleThreadExecutor()

    var list: MutableLiveData<List<ChatEntity>> = MutableLiveData()

    fun getChatList(): LiveData<List<ChatEntity>> {
        if (!sharedPreferences.getBoolean(Constants.CHAT_OPENED_FIRST_TIME, false)) {
            // launched chat for first time
            initiaiteChat()

        }
        return chatDao.getAllChat()
    }

    private fun initiaiteChat() {
        val id = dbChat.push().key
        val chatEntity = ChatEntity(Constants.CHAT_RECEIVED, "Welcome to Exam Preparation !!!", false, id!!)
        profileDetailsEntity.firebaseId?.let { dbChat.child(it).child(id).setValue(chatEntity) }
        insert(chatEntity)
        sharedPreferences.edit().putBoolean(Constants.CHAT_OPENED_FIRST_TIME, true).commit()
    }

    fun addUserInput(input :String){
        val id = dbChat.push().key
        val chatEntity = ChatEntity(Constants.CHAT_SENT, input, false, id!!)
        profileDetailsEntity.firebaseId?.let { dbChat.child(it).child(id).setValue(chatEntity) }
        insert(chatEntity)
    }

    fun insertList(list: List<ChatEntity>?) {
        mExecutor.execute { chatDao.insertChatList(list) }
    }

    fun insert(chatEntity: ChatEntity) {
        mExecutor.execute { chatDao.insertChat(chatEntity) }
    }

    fun update(chatEntity: ChatEntity) {
        mExecutor.execute { chatDao.updateChat(chatEntity) }
    }

    fun deleteChat(chatEntity: ChatEntity) {
        mExecutor.execute { chatDao.deleteChat(chatEntity) }
    }
}