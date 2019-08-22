package com.horizonlabs.kotlindemo.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import com.horizonlabs.kotlindemo.data.remote.ApiResponse
import com.horizonlabs.kotlindemo.data.remote.ApiStatus
import com.horizonlabs.kotlindemo.data.remote.api.UserApi
import com.horizonlabs.kotlindemo.model.UserEntity
import com.horizonlabs.kotlindemo.utility.Constants
import com.horizonlabs.kotlindemo.utility.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Singleton
class UserRepository(
    val userDao: UserDao,
    val userApi: UserApi,
    val sharedPreferences: SharedPreferences
) {

    private val mExecutor = Executors.newSingleThreadExecutor()

    var list: MutableLiveData<List<UserEntity>> = MutableLiveData()

    fun getUserList(): LiveData<List<UserEntity>> {
        if (!sharedPreferences.getBoolean(Constants.USER_INSERTED, false)) {
            getUserFromServer()
        }
        return userDao.getAllUsers()
    }

    fun getUserFromServer() {
        userApi.getUsers().enqueue(object : Callback<List<UserEntity>> {
            override fun onResponse(call: Call<List<UserEntity>>, response: Response<List<UserEntity>>) {
                if (response.isSuccessful) {
                    Logger.d("list fetch successful")
                    insert(response.body())
                    sharedPreferences.edit().putBoolean(Constants.USER_INSERTED, true).commit()
                }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
            }
        })
    }

    fun insert(list: List<UserEntity>?) {
        mExecutor.execute { userDao.saveUser(list) }
    }

    fun update(userEntity: UserEntity) {
        mExecutor.execute { userDao.updateUser(userEntity) }
    }

    fun getUser(): List<UserEntity>? {
        var temp: List<UserEntity>? = null
        mExecutor.execute {
            Logger.d("feching list from localdb")

            temp = userDao.getUsersSync()
            Logger.d("list fetched from localdb")
        }
        return temp
    }
}