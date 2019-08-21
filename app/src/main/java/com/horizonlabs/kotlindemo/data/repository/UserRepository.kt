package com.horizonlabs.kotlindemo.data.repository

import android.os.AsyncTask
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.MutableLiveData
import com.horizonlabs.kotlindemo.data.local.dao.UserDao
import com.horizonlabs.kotlindemo.data.remote.ApiResponse
import com.horizonlabs.kotlindemo.data.remote.ApiStatus
import com.horizonlabs.kotlindemo.data.remote.api.UserApi
import com.horizonlabs.kotlindemo.model.UserEntity
import com.horizonlabs.kotlindemo.utility.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Singleton


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
@Singleton
class UserRepository(val userDao: UserDao, val userApi: UserApi) {

    var userListResponse: MutableLiveData<ApiResponse<List<UserEntity>>> = MutableLiveData()


    fun getUserFromLocalDb(): MutableLiveData<ApiResponse<List<UserEntity>>> {

        /*var list = userDao.getAllUsers().value;
        if (list != null && !list.isEmpty()) {
            userListResponse.value = ApiResponse(ApiStatus.SUCCESS, list, "Fetched data from localdb")
        } else {
            userListResponse.value = ApiResponse(ApiStatus.FAILURE, null, "Failed to fetch list from localdb")
            getUserFromServer()
        }
*/
        userListResponse.value = ApiResponse(ApiStatus.LOADING, null, "Fetching data from server")
        getUserFromServer()
        return userListResponse;

    }

    fun getUserFromServer() {
        userApi.getUsers().enqueue(object : Callback<List<UserEntity>> {
            override fun onResponse(call: Call<List<UserEntity>>, response: Response<List<UserEntity>>) {
                if (response.isSuccessful) {
                    Logger.d("list fetch successful")
                    //userDao.saveUser(response.body())
                    userListResponse.value = ApiResponse(ApiStatus.SUCCESS, response.body(), "from server")
                }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                // TODO: 20-07-2019 HAndle failure cases
                userListResponse.value = ApiResponse(ApiStatus.FAILURE, null, t.message)
            }
        })
    }
}