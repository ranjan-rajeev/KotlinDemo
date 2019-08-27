package com.horizonlabs.kotlindemo.utility


/**
 * Created by Rajeev Ranjan -  ABPB on 20-08-2019.
 */
class Constants {
    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com"
        val CONNECT_TIMEOUT: Long = 60
        val READ_TIMEOUT: Long = 60
        val WRITE_TIMEOUT: Long = 60

        val SPLASH_TIME = 1000
        val USER_INSERTED = "user_inserted"
        val CHAT_OPENED_FIRST_TIME ="chat_opened_first_time"
        val CHAT_SENT: Byte = 1
        val CHAT_RECEIVED: Byte = 2
    }

}