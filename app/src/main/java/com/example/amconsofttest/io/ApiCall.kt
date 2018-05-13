package com.example.amconsofttest.io

import com.example.amconsofttest.io.rest.pojos.Client

import io.reactivex.Single
import retrofit2.http.GET

interface ApiCall {

    @get:GET("/users")
    val clients: Single<List<Client>>
}
