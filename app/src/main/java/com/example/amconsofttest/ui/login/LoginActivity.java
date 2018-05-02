package com.example.amconsofttest.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.amconsofttest.R;
import com.example.amconsofttest.ui.BaseActivity;
import com.example.amconsofttest.ui.BaseView;
import com.example.amconsofttest.ui.main.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView (R.id.login_facebook)
    LoginButton loginButton;
    @InjectPresenter
    LoginPresenter presenter;

    private CallbackManager callbackManager;

    public static Intent getNewIntent(Activity activity) {
        return new Intent(activity, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isNotLoggedIn = accessToken == null;
        callbackManager = CallbackManager.Factory.create();
        if(isNotLoggedIn){setupLoginButton();}
        else {openMainActivity();}
    }

    private void setupLoginButton(){
        loginButton.setReadPermissions("public_profile", "email");
        loginButton.registerCallback(callbackManager, presenter.getFacebookCallback(this::openMainActivity));
    }

    private void openMainActivity(){
        startActivity(MainActivity.getNewIntent(this));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void newUsersaved() {
        openMainActivity();
    }
}
