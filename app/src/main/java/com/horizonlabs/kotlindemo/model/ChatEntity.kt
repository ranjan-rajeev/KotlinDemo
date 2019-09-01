package com.horizonlabs.kotlindemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Entity
data class ChatEntity(
    var chatType: Int = 1,
    var chatDetails: String = "",
    var regex: String = "",
    var isUserInputRequired: Boolean = false,
    @PrimaryKey var chatId: String = "",
    var createdAt: String = "" + Calendar.getInstance().timeInMillis,
    var seqId: Int = 0
) {
}


