package com.horizonlabs.kotlindemo.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.horizonlabs.kotlindemo.data.local.dao.ExamDao
import com.horizonlabs.kotlindemo.data.repository.ExamRepository
import com.horizonlabs.kotlindemo.model.QuestionEntity
import javax.inject.Inject


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class ExamViewModel @Inject constructor(
    chatDao: ExamDao,
    sharedPreferences: SharedPreferences,
    database: FirebaseDatabase
) :
    ViewModel() {

    private val examRepository = ExamRepository(chatDao, sharedPreferences, database)

    fun getChatList(): LiveData<List<QuestionEntity>> {

        return examRepository.getQuestions();
    }

}