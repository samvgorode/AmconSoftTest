package com.example.amconsofttest.ui.base

import android.widget.Toast

import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.MvpFragment
import com.example.amconsofttest.ui.BaseView

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    override fun showProgressBar() {
        DialogUtil.displayProgressDialog(activity)
    }

    override fun hideProgressBar() {
        DialogUtil.hideProgressDialog()
    }

    override fun showToast(id: Int) {
        Toast.makeText(activity, getText(id), Toast.LENGTH_LONG).show()
    }

}
