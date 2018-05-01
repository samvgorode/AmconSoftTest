package com.example.amconsofttest.io.realm_models;

import io.realm.RealmObject;

public class AddressRealm extends RealmObject {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoRealm geo;

    public String getStreet() {
        return street;
    }

    public AddressRealm setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getSuite() {
        return suite;
    }

    public AddressRealm setSuite(String suite) {
        this.suite = suite;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressRealm setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public AddressRealm setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public GeoRealm getGeo() {
        return geo;
    }

    public AddressRealm setGeo(GeoRealm geo) {
        this.geo = geo;
        return this;
    }
}
