package com.horizonlabs.kotlindemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileDetailsEntity(
    val name: String = "Guest User",
    val email: String = "",
    val mobile: String = "",
    val firebaseId: String? = "",
    val deviceId: String=""
) {

    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0


}