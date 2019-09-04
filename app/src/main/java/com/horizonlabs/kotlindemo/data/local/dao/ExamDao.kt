package com.horizonlabs.kotlindemo.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.horizonlabs.kotlindemo.model.ExamEntity


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Dao
interface ExamDao {
    @Query("SELECT * FROM ExamEntity")
    fun getAllQuestions(): LiveData<List<ExamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuesList(list: List<ExamEntity>)

}