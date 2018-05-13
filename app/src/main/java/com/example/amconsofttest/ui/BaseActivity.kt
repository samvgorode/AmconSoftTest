package com.example.amconsofttest.ui

import android.annotation.SuppressLint
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.widget.Toast

import com.arellomobile.mvp.MvpAppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : MvpAppCompatActivity(), BaseView {
    override fun showToast(id: Int) {
        Toast.makeText(this, getText(id), Toast.LENGTH_LONG).show()
    }

    fun replaceFragment(@IdRes containerViewId: Int,
                        fragment: Fragment,
                        fragmentTag: String) {
        supportFragmentManager
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack("")
                .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
