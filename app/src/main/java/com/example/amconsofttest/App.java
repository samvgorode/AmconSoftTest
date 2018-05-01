package com.example.amconsofttest;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static Context context;
    private static Realm realm;

    public static Context getContext() {
        return context;
    }

    public static Realm getRealm() {
        return realm;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        FacebookSdk.sdkInitialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        AppEventsLogger.activateApp(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        context = this;
        realm = Realm.getDefaultInstance();
    }
}
