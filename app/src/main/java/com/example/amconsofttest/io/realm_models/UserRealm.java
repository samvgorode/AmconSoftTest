package com.example.amconsofttest.io.realm_models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserRealm extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String email;

    public String getId() {
        return id;
    }

    public UserRealm setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserRealm setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRealm setEmail(String email) {
        this.email = email;
        return this;
    }
}
