package com.example.amconsofttest.io.realm_models

import io.realm.RealmObject

open class AddressRealm : RealmObject() {

    private var street: String? = null
    private var suite: String? = null
    private var city: String? = null
    private var zipcode: String? = null
    private var geo: GeoRealm? = null

    fun getStreet(): String? {
        return street
    }

    fun setStreet(street: String): AddressRealm {
        this.street = street
        return this
    }

    fun getSuite(): String? {
        return suite
    }

    fun setSuite(suite: String): AddressRealm {
        this.suite = suite
        return this
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String): AddressRealm {
        this.city = city
        return this
    }

    fun getZipcode(): String? {
        return zipcode
    }

    fun setZipcode(zipcode: String): AddressRealm {
        this.zipcode = zipcode
        return this
    }

    fun getGeo(): GeoRealm? {
        return geo
    }

    fun setGeo(geo: GeoRealm): AddressRealm {
        this.geo = geo
        return this
    }
}
