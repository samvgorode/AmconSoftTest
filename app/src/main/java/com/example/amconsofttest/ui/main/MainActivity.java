package com.example.amconsofttest.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.amconsofttest.R;
import com.example.amconsofttest.ui.BaseActivity;
import com.example.amconsofttest.ui.login.LoginActivity;
import com.example.amconsofttest.ui.main.fragments.list.ClientListFragment;
import com.facebook.login.widget.ProfilePictureView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.profile_picture)
    ProfilePictureView pictureView;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_email)
    TextView userEmail;

    @InjectPresenter
    MainPresenter presenter;

    public static Intent getNewIntent(Activity activity) {
       return new Intent(activity, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupActionBar();
        setupDrawerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        replaceFragment(R.id.fragment_holder, ClientListFragment.newInstance(), "0");
    }

    private void setupActionBar(){
        if(getSupportActionBar()!=null){
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else drawer.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showToast(int id) {}

    private void setupDrawerView(){
        String id = presenter.getCurrentUserId();
        String name = presenter.getCurrentUserName();
        String email = presenter.getCurrentUserEmail();
        pictureView.setProfileId(id);
        userName.setText(name);
        userEmail.setText(email);
    }

    @OnClick(R.id.logout_button)
    void click(){
        presenter.logoutUser();
        startActivity(LoginActivity.getNewIntent(this));
        finish();
    }
}
