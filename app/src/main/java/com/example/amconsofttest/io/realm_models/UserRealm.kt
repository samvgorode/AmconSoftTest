package com.example.amconsofttest.io.realm_models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserRealm(
        @PrimaryKey
        open var id: String? = null,
        open var name: String? = null,
        open var email: String? = null
) : RealmObject()
