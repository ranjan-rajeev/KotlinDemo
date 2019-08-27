package com.horizonlabs.kotlindemo.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.google.firebase.database.FirebaseDatabase
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.view.base.BaseActivity


class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.content_splash_screen)
        var database = FirebaseDatabase.getInstance()
        val dbChat = database.getReference("chat")
        val id = dbChat.push().key
        Handler().postDelayed({
            val mainIntent = Intent(this, ChatActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 1000)
    }
}
