package com.horizonlabs.kotlindemo.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.horizonlabs.kotlindemo.data.local.dao.ExamDao
import com.horizonlabs.kotlindemo.model.ExamEntity
import com.horizonlabs.kotlindemo.model.ProfileDetailsEntity
import com.horizonlabs.kotlindemo.utility.Constants
import com.horizonlabs.kotlindemo.utility.Logger
import java.util.concurrent.Executors
import javax.inject.Singleton


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Singleton
class ExamRepository(
    val examDao: ExamDao,
    val sharedPreferences: SharedPreferences,
    val database: FirebaseDatabase
) {


    var profileDetailsEntity: ProfileDetailsEntity? = null
    val dbQues = database.getReference("questions")
    private val mExecutor = Executors.newSingleThreadExecutor()
    var listLiveData: MutableLiveData<List<ExamEntity>> = MutableLiveData()
    var list: MutableList<ExamEntity> = ArrayList()

    init {
        fetchUser()
    }

    fun getQuestions(): LiveData<List<ExamEntity>> {
        fetchQuestionFromServer();
        return examDao.getAllQuestions()
    }

    fun fetchQuestionFromServer() {
        val query = dbQues.orderByChild("selectTopic").equalTo("Politics")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    for (data in dataSnapshot.children) {
                        val examEntity = data.getValue(ExamEntity::class.java)

                        examEntity?.let {
                            list.add(examEntity)
                            Logger.d("{${examEntity.quesString}}   " + examEntity.option.get(1))
                        }
                    }
                    listLiveData.postValue(list)
                    insertList(list)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    fun insertList(list: List<ExamEntity>) {
        mExecutor.execute { examDao.insertQuesList(list) }
    }

    /*fun insert(examEntity: ExamEntity) {
        mExecutor.execute { examDao.insertChat(examEntity) }
    }

    fun update(examEntity: ExamEntity) {
        mExecutor.execute { examDao.updateChat(examEntity) }
    }

    fun deleteChat(examEntity: ExamEntity) {
        mExecutor.execute { examDao.deleteChat(examEntity) }
    }*/


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