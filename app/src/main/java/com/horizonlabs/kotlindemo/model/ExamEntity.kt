package com.horizonlabs.kotlindemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.horizonlabs.kotlindemo.data.local.converter.StringConverter
import java.util.ArrayList

@Entity
data class ExamEntity(

    @PrimaryKey @SerializedName("firebaseId") val firebaseId: String = "",
    @TypeConverters(StringConverter::class)
    @SerializedName("questions") val questions: List<String> = ArrayList<String>(),
    @SerializedName("examId") val examId: Int = 0,
    @SerializedName("examName") val examName: String = "",
    @SerializedName("totalQuest") val totalQues: Int = 0,
    @SerializedName("quesString") val quesAttemp: Int = 0,
    @SerializedName("questionUrl") val correctQues: Int = 0,
    @SerializedName("relatedExam") val incorrectQues: Int = 0,
    @SerializedName("selectSubject") val marksObtained: Double = 0.0,
    @SerializedName("selectTopic") val totalMarks: Double = 0.0
)