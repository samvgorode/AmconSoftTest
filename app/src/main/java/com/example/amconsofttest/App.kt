package com.example.amconsofttest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate

import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger

import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        context = this.applicationContext
        realm = Realm.getDefaultInstance()
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
        FacebookSdk.sdkInitialize(applicationContext)
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true)
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
        }
        AppEventsLogger.activateApp(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

    }


    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        @SuppressLint("StaticFieldLeak")
        lateinit var realm: Realm
    }
}
