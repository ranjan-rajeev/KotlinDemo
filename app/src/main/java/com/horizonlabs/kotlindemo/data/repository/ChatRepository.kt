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
import com.horizonlabs.kotlindemo.utility.Constants
import java.util.concurrent.Executors
import javax.inject.Singleton


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Singleton
class ChatRepository(
    val chatDao: ChatDao,
    val sharedPreferences: SharedPreferences,
    val database: FirebaseDatabase
) {


    val dbChat = database.getReference("chat")
    private val mExecutor = Executors.newSingleThreadExecutor()

    var list: MutableLiveData<List<ChatEntity>> = MutableLiveData()

    fun getChatList(): LiveData<List<ChatEntity>> {
        if (!sharedPreferences.getBoolean(Constants.CHAT_OPENED_FIRST_TIME, false)) {
            getChatFromServer()
        }
        return chatDao.getAllChat()
    }

    private fun getChatFromServer() {
        dbChat.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var tempList = ArrayList<ChatEntity>()
                for (postSnapshot in dataSnapshot.children) {
                    //getting artist
                    val chatEntity = postSnapshot.getValue(ChatEntity::class.java)
                    chatEntity?.let { tempList.add(it) }
                }
                insertList(tempList)
                sharedPreferences.edit().putBoolean(Constants.CHAT_OPENED_FIRST_TIME, true)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
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

    fun addInitialChat(){
        val chatEntity = ChatEntity(Constants.CHAT_RECEIVED,"Welcome to Exam Preparation !!!")
        val chatEntity = ChatEntity(Constants.CHAT_RECEIVED,"My Name is Kia and i will be guiding you at various stages of your Exam Preparation.")
        val chatEntity = ChatEntity(Constants.CHAT_RECEIVED,"I will be glad to know more about you to help you in a better way.")
        val id = dbChat.push().getKey()

        dbComments.child(id!!).setValue(commentsEntity)
    }
    var chat  = getInitialChat().addInitialChat()
}