package com.horizonlabs.kotlindemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Entity
data class ChatEntity(
    var chatType: Int,
    var chatDetails: String,
    var isUserInputRequired: Boolean,
    var firebaseId: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    public var chatId: Int = 0
}


