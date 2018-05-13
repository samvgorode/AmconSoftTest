package com.example.amconsofttest.io.realm_models

import io.realm.RealmObject

open class GeoRealm : RealmObject() {

    private var lat: String? = null
    private var lng: String? = null

    fun getLat(): String? {
        return lat
    }

    fun setLat(lat: String): GeoRealm {
        this.lat = lat
        return this
    }

    fun getLng(): String? {
        return lng
    }

    fun setLng(lng: String): GeoRealm {
        this.lng = lng
        return this
    }
}
