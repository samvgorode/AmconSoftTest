package com.example.amconsofttest.ui;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

@SuppressLint ("Registered")
public class BaseActivity extends MvpAppCompatActivity implements BaseView {
    @Override
    public void showToast(int id) {
        Toast.makeText(this, getText(id), Toast.LENGTH_LONG).show();
    }

    public void replaceFragment(@IdRes int containerViewId,
                                @NonNull Fragment fragment,
                                String fragmentTag,
                                @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commitAllowingStateLoss();
    }
}
