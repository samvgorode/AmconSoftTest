package com.example.amconsofttest.io;

import com.example.amconsofttest.io.rest.pojos.Client;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiCall {

    @GET ("/users")
    Single<List<Client>> getClients();
}
