package com.horizonlabs.kotlindemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Entity
data class ChatEntity(
    var chatType: Byte,
    var chatDetails: String
) {
    @PrimaryKey(autoGenerate = true)
    val chatId: Int = 0
}


