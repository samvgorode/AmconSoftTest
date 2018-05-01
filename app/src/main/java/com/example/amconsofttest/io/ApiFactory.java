package com.example.amconsofttest.io;

import android.support.annotation.NonNull;

import com.example.amconsofttest.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 15;
    private static final int TIMEOUT = 15;
    private static final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private static boolean addLogInterseptor = false;

    @NonNull
    private static Retrofit getRest() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        if (!addLogInterseptor) {
            addLogInterseptor = true;
            builder.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Cache-Control", "no-cache")
                        .method(original.method(), original.body());
                Request request = requestBuilder
                        .cacheControl(new CacheControl.Builder().noCache().build())
                        .build();
                return chain.proceed(request);
            });
        }

        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    @NonNull
    public static ApiCall getApiService() {
        return getRest().create(ApiCall.class);
    }
}
