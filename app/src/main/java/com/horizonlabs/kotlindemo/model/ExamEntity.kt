package com.horizonlabs.kotlindemo.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

@Entity
data class ExamEntity(
    @SerializedName("ansExplanation") val ansExplanation: String="",
    @SerializedName("correctAns") val correctAns: Int=0,
    @SerializedName("diffLevel") val diffLevel: Int=0,
    @SerializedName("expTime") val expTime: Int=0,
    @SerializedName("firebaseId") val firebaseId: String="",
    @SerializedName("option") val option: List<String> = ArrayList<String>(),
    @SerializedName("quesId") val quesId: Int=0,
    @SerializedName("quesLang") val quesLang: Int=0,
    @SerializedName("quesString") val quesString: String="",
    @SerializedName("questionUrl") val questionUrl: String="",
    @SerializedName("relatedExam") val relatedExam: Int=0,
    @SerializedName("selectSubject") val selectSubject: Int=0,
    @SerializedName("selectTopic") val selectTopic: String=""
)