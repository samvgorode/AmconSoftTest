package com.example.amconsofttest.io.realm_models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CompanyRealm extends RealmObject {

    @PrimaryKey
    private String name;
    private String catchPhrase;
    private String bs;

    public String getName() {
        return name;
    }

    public CompanyRealm setName(String name) {
        this.name = name;
        return this;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public CompanyRealm setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
        return this;
    }

    public String getBs() {
        return bs;
    }

    public CompanyRealm setBs(String bs) {
        this.bs = bs;
        return this;
    }
}
