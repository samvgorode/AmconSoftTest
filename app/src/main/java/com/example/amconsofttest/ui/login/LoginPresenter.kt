package com.example.amconsofttest.ui.login

import android.os.Bundle
import android.text.TextUtils

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.amconsofttest.App
import com.example.amconsofttest.R
import com.example.amconsofttest.io.realm_models.UserRealm
import com.example.amconsofttest.ui.BaseView
import com.example.amconsofttest.ui.Constants.EMAIL
import com.example.amconsofttest.ui.Constants.ID
import com.example.amconsofttest.ui.Constants.NAME
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult

import org.json.JSONException


@InjectViewState
class LoginPresenter : MvpPresenter<BaseView>() {

    private fun getInfoFromFacebook(token: AccessToken) {
        val request: GraphRequest
        request = GraphRequest.newMeRequest(token
        ) { `object`, _ ->
            var name: String? = null
            var email: String? = null
            var id: String? = null

            try {
                name = `object`.getString(NAME)
                id = `object`.getString(ID)
                email = `object`.getString(EMAIL)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            val realm = App.realm
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(id)) {
                realm.beginTransaction()
                realm.copyToRealmOrUpdate(UserRealm(id, name, email))
                realm.commitTransaction()
                if (realm.where(UserRealm::class.java).findFirst() != null) {
                    viewState.newUsersaved()
                }
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "id,name,email")
        request.parameters = parameters
        request.executeAsync()
    }

    internal fun getFacebookCallback(): FacebookCallback<LoginResult> {
        return object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                getInfoFromFacebook(loginResult.accessToken)
            }

            override fun onCancel() {
                viewState.showToast(R.string.fb_login_cancelled)
            }

            override fun onError(exception: FacebookException) {
                viewState.showToast(R.string.fb_login_error)
            }
        }
    }
}
