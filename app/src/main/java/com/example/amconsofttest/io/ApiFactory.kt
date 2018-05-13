package com.example.amconsofttest.io

import com.example.amconsofttest.BuildConfig

import java.util.concurrent.TimeUnit

import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val CONNECT_TIMEOUT = 15
    private val WRITE_TIMEOUT = 15
    private val TIMEOUT = 15
    private val builder = OkHttpClient.Builder()
    private var addLogInterseptor = false

    private val rest: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

    private val client: OkHttpClient
        get() {
            if (!addLogInterseptor) {
                addLogInterseptor = true
                builder.addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Cache-Control", "no-cache")
                            .method(original.method(), original.body())
                    val request = requestBuilder
                            .cacheControl(CacheControl.Builder().noCache().build())
                            .build()
                    chain.proceed(request)
                }
            }

            builder.connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            return builder.build()
        }

    val apiService: ApiCall
        get() = rest.create(ApiCall::class.java)
}
