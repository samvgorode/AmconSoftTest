package com.example.amconsofttest.ui.login;

import android.os.Bundle;
import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.amconsofttest.App;
import com.example.amconsofttest.R;
import com.example.amconsofttest.io.realm_models.UserRealm;
import com.example.amconsofttest.ui.BaseView;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;

import org.json.JSONException;

import static com.example.amconsofttest.ui.Constants.EMAIL;
import static com.example.amconsofttest.ui.Constants.ID;
import static com.example.amconsofttest.ui.Constants.NAME;
import io.realm.Realm;

@InjectViewState
public class LoginPresenter extends MvpPresenter<BaseView> {

    private void getInfoFromFacebook(AccessToken token) {
        GraphRequest request;
        request = GraphRequest.newMeRequest(token,
                (object, response) -> {
                    String name = null;
                    String email = null;
                    String id = null;

                    try {
                        name = object.getString(NAME);
                        id = object.getString(ID);
                        email = object.getString(EMAIL);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Realm realm = App.getRealm();
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(id)) {
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(new UserRealm().setId(id).setName(name).setEmail(email));
                        realm.commitTransaction();
                        if(realm.where(UserRealm.class).findFirst()!=null){getViewState().newUsersaved();}
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    FacebookCallback<LoginResult> getFacebookCallback(Runnable openMainActivity){
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getInfoFromFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {getViewState().showToast(R.string.fb_login_cancelled);}

            @Override
            public void onError(FacebookException exception) {getViewState().showToast(R.string.fb_login_error);}
        };
    }
}
