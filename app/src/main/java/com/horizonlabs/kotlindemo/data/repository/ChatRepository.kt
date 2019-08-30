package com.horizonlabs.kotlindemo.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.gson.Gson
import com.horizonlabs.kotlindemo.data.local.dao.ChatDao
import com.horizonlabs.kotlindemo.model.ChatEntity
import com.horizonlabs.kotlindemo.model.ProfileDetailsEntity
import com.horizonlabs.kotlindemo.model.SequenceEntity
import com.horizonlabs.kotlindemo.utility.Constants
import com.horizonlabs.kotlindemo.utility.Logger
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


    var profileDetailsEntity: ProfileDetailsEntity? = null
    val dbChat = database.getReference("chat")
    val dbSequence = database.getReference("sequence")
    private val mExecutor = Executors.newSingleThreadExecutor()
    var list: MutableLiveData<List<ChatEntity>> = MutableLiveData()

    init {
        fetchUser()
    }

    fun getChatList(): LiveData<List<ChatEntity>> {

        if (!sharedPreferences.getBoolean(Constants.CHAT_OPENED_FIRST_TIME, false)) {
            // launched chat for first time
            fetchNextChat(1)
            sharedPreferences.edit().putBoolean(Constants.CHAT_OPENED_FIRST_TIME, true).apply()

        }
        return chatDao.getAllChat()
    }

    private fun initiaiteChat() {
        val id = dbChat.push().key
        val chatEntity = ChatEntity(Constants.CHAT_RECEIVED, "Welcome to Exam Preparation !!!", false, id!!)
        profileDetailsEntity?.firebaseId?.let { dbChat.child(it).child(id).setValue(chatEntity) }
        insert(chatEntity)
        sharedPreferences.edit().putBoolean(Constants.CHAT_OPENED_FIRST_TIME, true).apply()
    }

    fun addUserInput(input: String) {

        mExecutor.execute {
            val seq: Int = chatDao.getMaxSeq()
            val id = dbChat.push().key
            val chatEntity = ChatEntity(Constants.CHAT_SENT, input, false, id!!, seqId = seq)
            profileDetailsEntity?.firebaseId?.let { dbChat.child(it).child(id).setValue(chatEntity) }
            insert(chatEntity)
        }
    }

    fun fetchNextChat(sequenceId: Int) {
        var query: Query = dbSequence.orderByChild("seqNo")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (data in dataSnapshot.children) {
                        val sequenceEntity = data.getValue(SequenceEntity::class.java)

                        sequenceEntity?.let {
                            if (sequenceEntity.seqNo == sequenceId) {
                                Logger.d(sequenceEntity.question)
                                addChatFromSequence(sequenceEntity)
                            }
                        }
                    }
                }
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

    fun addChatFromSequence(sequenceEntity: SequenceEntity) {
        mExecutor.execute {
            val chatEntity = ChatEntity(
                sequenceEntity.chatType,
                sequenceEntity.question,
                sequenceEntity.isUserInputRequired,
                sequenceEntity.firebaseId,
                seqId = sequenceEntity.nextSeqId
            )
            if (profileDetailsEntity == null) {
                fetchUser()
            }
            profileDetailsEntity?.firebaseId?.let {
                dbChat.child(it).child(sequenceEntity.firebaseId).setValue(chatEntity)
            }

            if (chatEntity.seqId in 3..5) {
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            insert(chatEntity)
        }
    }

    fun fetchUser() {
        var str = sharedPreferences.getString(Constants.USER_DETAILS, "")
        if (!str.equals("")) {
            profileDetailsEntity = Gson().fromJson(
                sharedPreferences.getString(Constants.USER_DETAILS, ""),
                ProfileDetailsEntity::class.java
            )
        }
    }
}