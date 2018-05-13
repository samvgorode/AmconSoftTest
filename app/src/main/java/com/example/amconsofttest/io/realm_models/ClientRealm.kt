package com.example.amconsofttest.io.realm_models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ClientRealm : RealmObject() {

    @PrimaryKey
    private var id: Int? = null
    private var name: String? = null
    private var username: String? = null
    private var email: String? = null
    private var address: AddressRealm? = null
    private var phone: String? = null
    private var website: String? = null
    private var company: CompanyRealm? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?): ClientRealm {
        this.id = id
        return this
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String): ClientRealm {
        this.name = name
        return this
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String): ClientRealm {
        this.username = username
        return this
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String): ClientRealm {
        this.email = email
        return this
    }

    fun getAddress(): AddressRealm? {
        return address
    }

    fun setAddress(address: AddressRealm): ClientRealm {
        this.address = address
        return this
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String): ClientRealm {
        this.phone = phone
        return this
    }

    fun getWebsite(): String? {
        return website
    }

    fun setWebsite(website: String): ClientRealm {
        this.website = website
        return this
    }

    fun getCompany(): CompanyRealm? {
        return company
    }

    fun setCompany(company: CompanyRealm): ClientRealm {
        this.company = company
        return this
    }
}
