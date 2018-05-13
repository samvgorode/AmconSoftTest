package com.example.amconsofttest.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.amconsofttest.R
import com.example.amconsofttest.ui.BaseActivity
import com.example.amconsofttest.ui.login.LoginActivity
import com.example.amconsofttest.ui.main.fragments.list.ClientListFragment
import com.facebook.login.widget.ProfilePictureView

class MainActivity : BaseActivity() {

    @BindView(R.id.drawer_layout) lateinit var drawer: DrawerLayout
    @BindView(R.id.profile_picture) lateinit var pictureView: ProfilePictureView
    @BindView(R.id.user_name) lateinit var userName: TextView
    @BindView(R.id.user_email) lateinit var userEmail: TextView

    @InjectPresenter internal lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setupActionBar()
        setupDrawerView()
    }

    override fun onResume() {
        super.onResume()
        replaceFragment(R.id.fragment_holder, ClientListFragment.newInstance(), "0")
    }

    private fun setupActionBar() {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawer?.run {
                    if (this.isDrawerOpen(GravityCompat.START)) {
                        this.closeDrawer(GravityCompat.START)
                    } else{
                        this.openDrawer(GravityCompat.START)
                    }
                }


                      // OPEN DRAWER
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        drawer?.run {
            if (this.isDrawerOpen(GravityCompat.START)) {
                this.closeDrawer(GravityCompat.START)
            } else{
                super.onBackPressed()
            }
        }
    }

    override fun showToast(id: Int) {}

    private fun setupDrawerView() {
        val id = presenter.currentUserId
        val name = presenter.currentUserName
        val email = presenter.currentUserEmail
        pictureView.profileId = id
        userName.text = name
        userEmail.text = email
    }

    @OnClick(R.id.logout_button)
    internal fun click() {
        presenter.logoutUser()
        startActivity(LoginActivity.getNewIntent(this))
        finish()
    }

    companion object {

        fun getNewIntent(activity: Activity): Intent {
            return Intent(activity, MainActivity::class.java)
        }
    }
}
