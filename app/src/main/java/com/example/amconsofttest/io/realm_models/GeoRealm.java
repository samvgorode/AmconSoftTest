package com.example.amconsofttest.io.realm_models;

import io.realm.RealmObject;

public class GeoRealm extends RealmObject {

    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public GeoRealm setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public GeoRealm setLng(String lng) {
        this.lng = lng;
        return this;
    }
}
