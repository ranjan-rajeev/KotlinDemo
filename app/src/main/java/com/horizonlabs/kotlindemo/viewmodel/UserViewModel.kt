package com.horizonlabs.kotlindemo.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import com.horizonlabs.kotlindemo.data.remote.api.UserApi
import com.horizonlabs.kotlindemo.data.repository.UserRepository
import com.horizonlabs.kotlindemo.model.UserEntity
import javax.inject.Inject


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class UserViewModel @Inject constructor(userDao: UserDao, userApi: UserApi, sharedPreferences: SharedPreferences) :
    ViewModel() {

    private val userRepository = UserRepository(userDao, userApi, sharedPreferences)

    fun getUser(): LiveData<List<UserEntity>> {
        return userRepository.getUserList();
    }

}