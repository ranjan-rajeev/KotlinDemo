package com.horizonlabs.kotlindemo.data.remote.api

import com.horizonlabs.kotlindemo.model.UserEntity
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
interface UserApi {
    @GET("/users")
    fun getUsers(): Call<List<UserEntity>>
}