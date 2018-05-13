@file:Suppress("DEPRECATION")

package com.example.amconsofttest.ui.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable

import com.example.amconsofttest.R

object DialogUtil {
    private var sProgressDialog: ProgressDialog? = null

    /**
     * Shows the progress dialog
     *
     * @param activity - calling activity
     */
    fun displayProgressDialog(activity: Activity?) {
        if (sProgressDialog != null && sProgressDialog!!.isShowing)
            return

        if (activity != null) {
            sProgressDialog = ProgressDialog(activity)
            try {
                sProgressDialog!!.show()
                sProgressDialog!!.setCanceledOnTouchOutside(false)
                sProgressDialog!!.setCancelable(false)
                sProgressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
                sProgressDialog!!.setContentView(R.layout.progress_dialog_layout)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun hideProgressDialog() {

        if (sProgressDialog != null && sProgressDialog!!.isShowing) {
            try {
                sProgressDialog!!.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            sProgressDialog = null
        }
    }

}

