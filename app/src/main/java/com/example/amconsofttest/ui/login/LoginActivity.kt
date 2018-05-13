package com.example.amconsofttest.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.amconsofttest.R
import com.example.amconsofttest.R.id.login_facebook
import com.example.amconsofttest.ui.BaseActivity
import com.example.amconsofttest.ui.main.MainActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.login.widget.LoginButton

class LoginActivity : BaseActivity() {

    @InjectPresenter
    internal lateinit var presenter: LoginPresenter

    @BindView(R.id.login_facebook) lateinit var loginFacebook: LoginButton

    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
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
        loginFacebook.setReadPermissions("public_profile", "email")
        loginFacebook.registerCallback(callbackManager, presenter.getFacebookCallback())
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
