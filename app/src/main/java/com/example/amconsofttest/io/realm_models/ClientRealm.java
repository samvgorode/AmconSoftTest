package com.example.amconsofttest.io.realm_models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ClientRealm extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String name;
    private String username;
    private String email;
    private AddressRealm address;
    private String phone;
    private String website;
    private CompanyRealm company;

    public Integer getId() {
        return id;
    }

    public ClientRealm setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClientRealm setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ClientRealm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientRealm setEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressRealm getAddress() {
        return address;
    }

    public ClientRealm setAddress(AddressRealm address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ClientRealm setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public ClientRealm setWebsite(String website) {
        this.website = website;
        return this;
    }

    public CompanyRealm getCompany() {
        return company;
    }

    public ClientRealm setCompany(CompanyRealm company) {
        this.company = company;
        return this;
    }
}
