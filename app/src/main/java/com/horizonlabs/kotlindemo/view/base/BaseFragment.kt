package com.horizonlabs.kotlindemo.view.base

import android.app.ProgressDialog
import android.content.Context
import androidx.fragment.app.Fragment


/**
 * Created by Rajeev Ranjan -  ABPB on 07-08-2019.
 */
open class BaseFragment : Fragment() {
    var dialog: ProgressDialog? = null

    fun showDialog(context: Context?, msg: String? = "Loading") {
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