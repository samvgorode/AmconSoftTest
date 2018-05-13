package com.example.amconsofttest.io.realm_models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CompanyRealm : RealmObject() {

    @PrimaryKey
    private var name: String? = null
    private var catchPhrase: String? = null
    private var bs: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String): CompanyRealm {
        this.name = name
        return this
    }

    fun getCatchPhrase(): String? {
        return catchPhrase
    }

    fun setCatchPhrase(catchPhrase: String): CompanyRealm {
        this.catchPhrase = catchPhrase
        return this
    }

    fun getBs(): String? {
        return bs
    }

    fun setBs(bs: String): CompanyRealm {
        this.bs = bs
        return this
    }
}
