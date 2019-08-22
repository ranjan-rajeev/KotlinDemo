package com.horizonlabs.kotlindemo.view.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


/**
 * Created by Rajeev Ranjan -  ABPB on 07-08-2019.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }
    var dialog: ProgressDialog? = null

    fun showDialog(context: Context, msg: String? = "Loading") {
        if (dialog == null) {
            dialog = ProgressDialog.show(context, "", msg, true)
        } else {
            if (!dialog!!.isShowing) {
                dialog = ProgressDialog.show(context, "", msg, true)
            }
        }
    }

    fun cancelDialog() {
        dialog?.dismiss()
    }
}