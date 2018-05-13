package com.example.amconsofttest.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.amconsofttest.R
import com.example.amconsofttest.ui.BaseActivity
import com.example.amconsofttest.ui.login.LoginActivity
import com.example.amconsofttest.ui.main.fragments.list.ClientListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                drawer_layout?.run {
                    if (this.isDrawerOpen(GravityCompat.START)) {
                        this.closeDrawer(GravityCompat.START)
                    } else {
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
        drawer_layout?.run {
            if (this.isDrawerOpen(GravityCompat.START)) {
                this.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun showToast(id: Int) {}

    private fun setupDrawerView() {
        val id = presenter.currentUserId
        val name = presenter.currentUserName
        val email = presenter.currentUserEmail
        profile_picture.profileId = id
        user_name.text = name
        user_email.text = email
        logout_button.setOnClickListener { logoutClicked() }
    }

    fun logoutClicked() {
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
