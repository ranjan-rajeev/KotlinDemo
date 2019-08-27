package com.horizonlabs.kotlindemo.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.horizonlabs.kotlindemo.R
import com.horizonlabs.kotlindemo.model.ProfileDetailsEntity
import com.horizonlabs.kotlindemo.utility.PreferenceManager
import com.horizonlabs.kotlindemo.utility.Util
import com.horizonlabs.kotlindemo.view.base.BaseActivity
import javax.inject.Inject


class SplashActivity : BaseActivity() {


    lateinit var preferenceManager: PreferenceManager
    @Inject
    lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.content_splash_screen)

        preferenceManager = PreferenceManager(this)
        database = FirebaseDatabase.getInstance()
        val userString = preferenceManager.getUser()
        if (userString.equals("")) {
            val androidId = Util.getAndroidId(this)

            val dbUser = database.getReference("users")
            val query = dbUser.orderByChild("deviceId").equalTo(androidId)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Toast.makeText(this@SplashActivity, "User Exhist on server", Toast.LENGTH_SHORT).show()
                        for (dataSnapshot in dataSnapshot.children) {
                            val profile = dataSnapshot.getValue(ProfileDetailsEntity::class.java)
                            preferenceManager.storeUser(profile)
                            break
                        }
                    } else {
                        Toast.makeText(this@SplashActivity, "User Doesn't Exhist on server", Toast.LENGTH_SHORT).show()
                        val id = dbUser.push().key
                        val profileDetailsEntity = ProfileDetailsEntity(firebaseId = id, deviceId = androidId)
                        id?.let {
                            dbUser.child(id).setValue(profileDetailsEntity)
                            preferenceManager.storeUser(profileDetailsEntity)
                        }
                    }
                }

                override fun onCancelled(dbError: DatabaseError) {
                }
            })
        } else {
            Toast.makeText(this@SplashActivity, "User Exhist on Local", Toast.LENGTH_SHORT).show()
        }

        Handler().postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 1000)
    }

    fun dtoreUserInSharedPreference(profileDetailsEntity: ProfileDetailsEntity?) {

    }
}
