package com.horizonlabs.kotlindemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import com.horizonlabs.kotlindemo.data.remote.ApiResponse
import com.horizonlabs.kotlindemo.data.remote.api.UserApi
import com.horizonlabs.kotlindemo.data.repository.UserRepository
import com.horizonlabs.kotlindemo.model.UserEntity
import javax.inject.Inject


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class UserViewModel @Inject constructor(userDao: UserDao, userApi: UserApi) : ViewModel() {

    private val userRepository = UserRepository(userDao, userApi)

    fun getUser(): MutableLiveData<ApiResponse<List<UserEntity>>> {
        return userRepository.getUserFromLocalDb();
    }

}