package com.example.amconsofttest.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.amconsofttest.R
import com.example.amconsofttest.ui.BaseActivity
import com.example.amconsofttest.ui.main.MainActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    @InjectPresenter
    internal lateinit var presenter: LoginPresenter

    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val accessToken = AccessToken.getCurrentAccessToken()
        val isNotLoggedIn = accessToken == null
        callbackManager = CallbackManager.Factory.create()
        if (isNotLoggedIn) {
            setupLoginButton()
        } else {
            openMainActivity()
        }
    }

    private fun setupLoginButton() {
        login_facebook.setReadPermissions("public_profile", "email")
        login_facebook.registerCallback(callbackManager, presenter.getFacebookCallback())
    }


    private fun openMainActivity() {
        startActivity(MainActivity.getNewIntent(this))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun newUsersaved() {
        openMainActivity()
    }

    companion object {

        fun getNewIntent(activity: Activity): Intent {
            return Intent(activity, LoginActivity::class.java)
        }
    }
}
