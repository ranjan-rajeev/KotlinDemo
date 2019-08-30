package com.horizonlabs.kotlindemo.model


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
data class SequenceEntity(
    var answerType: Int = 1,
    var chatType: Int = 0,
    var firebaseId: String = "",

    @field:JvmField
    var isUserInputRequired: Boolean = false,
    var nextSeqId: Int = 0,
    var question: String = "",
    var regex: String = "",
    var seqNo: Int = 0
) {
}


