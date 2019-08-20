package com.horizonlabs.kotlindemo.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Entity
data class UserEntity(
    @Embedded
    var address: Address,
    @Embedded
    var company: Company,
    var email: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var phone: String,
    var username: String,
    var website: String
)

data class Company(
    var bs: String,
    var catchPhrase: String,
    @SerializedName("company_name")
    var name: String
)

data class Address(
    var city: String,

    @Embedded
    var geo: Geo,
    var street: String,
    var suite: String,
    var zipcode: String
)

data class Geo(
    var lat: String,
    var lng: String
)