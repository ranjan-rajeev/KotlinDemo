package com.horizonlabs.kotlindemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.horizonlabs.kotlindemo.data.local.converter.StringConverter
import java.util.ArrayList

@Entity
data class QuestionEntity(
    @SerializedName("ansExplanation") val ansExplanation: String = "",
    @SerializedName("correctAns") val correctAns: Int = -2,
    @SerializedName("selectedAns") var selectedAns: Int = -1,
    @SerializedName("diffLevel") val diffLevel: Int = 0,
    @SerializedName("expTime") val expTime: Int = 0,
    @PrimaryKey @SerializedName("firebaseId") val firebaseId: String = "",

    @TypeConverters(StringConverter::class)
    @SerializedName("option") val option: List<String> = ArrayList<String>(),
    @SerializedName("quesId") val quesId: Int = 0,
    @SerializedName("quesLang") val quesLang: Int = 0,
    @SerializedName("quesString") val quesString: String = "",
    @SerializedName("questionUrl") val questionUrl: String = "",
    @SerializedName("relatedExam") val relatedExam: Int = 0,
    @SerializedName("selectSubject") val selectSubject: Int = 0,
    @SerializedName("selectTopic") val selectTopic: String = ""
)