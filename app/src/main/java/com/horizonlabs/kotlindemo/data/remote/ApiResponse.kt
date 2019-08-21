package com.horizonlabs.kotlindemo.data.remote


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class ApiResponse<T>(var status: ApiStatus, var data: T?, var message: String?) {

    companion object {
        fun <T> success(data: T?, message: String = "success"): ApiResponse<T> {
            return ApiResponse(ApiStatus.SUCCESS, data, message)
        }

        fun <T> failure(data: T?, message: String = "Failure"): ApiResponse<T> {
            return ApiResponse(ApiStatus.FAILURE, data, message)
        }

        fun <T> error(data: T?, message: String = "Please try after sometime"): ApiResponse<T> {
            return ApiResponse(ApiStatus.ERROR, data, message)
        }

        fun <T> loading(data: T?, message: String = "Loading data.."): ApiResponse<T> {
            return ApiResponse(ApiStatus.LOADING, data, message)
        }

    }

}