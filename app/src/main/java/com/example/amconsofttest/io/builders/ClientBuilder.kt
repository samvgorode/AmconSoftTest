package com.example.amconsofttest.io.builders

import com.example.amconsofttest.App
import com.example.amconsofttest.io.realm_models.AddressRealm
import com.example.amconsofttest.io.realm_models.ClientRealm
import com.example.amconsofttest.io.realm_models.CompanyRealm
import com.example.amconsofttest.io.realm_models.GeoRealm
import com.example.amconsofttest.io.rest.pojos.Address
import com.example.amconsofttest.io.rest.pojos.Client
import com.example.amconsofttest.io.rest.pojos.Company
import com.example.amconsofttest.io.rest.pojos.Geo

import io.realm.Realm
import io.realm.RealmList

object ClientBuilder {

    private val realm: Realm
        get() = App.realm!!

    fun build(clients: List<Client>): RealmList<ClientRealm> {
        val realmList = RealmList<ClientRealm>()
        for (client in clients) {
                val clientRealm = buildOneClientRealm(client)
                realmList.add(clientRealm)

        }
        return realmList
    }

    private fun buildOneClientRealm(client: Client?): ClientRealm {
        if (client != null) {
            val clientRealm: ClientRealm
            realm.beginTransaction()
            clientRealm = realm.copyToRealmOrUpdate(ClientRealm()
                    .setId(client.id)
                    .setName(client.name!!)
                    .setUsername(client.username!!)
                    .setEmail(client.email!!)
                    .setAddress(buildOneAddressRealm(client.address))
                    .setPhone(client.phone!!)
                    .setWebsite(client.website!!)
                    .setCompany(buildOneCompanyRealm(client.company)))
            realm.commitTransaction()
            return clientRealm
        } else {
            return ClientRealm()
        }

    }

    private fun buildOneCompanyRealm(company: Company?): CompanyRealm {
        return if (company != null) {
            realm.copyToRealmOrUpdate(CompanyRealm()
                    .setName(company.name!!)
                    .setCatchPhrase(company.catchPhrase!!)
                    .setBs(company.bs!!))
        } else {
            CompanyRealm()
        }
    }

    private fun buildOneAddressRealm(address: Address?): AddressRealm {
        return if (address != null) {
            realm.copyToRealm(AddressRealm()
                    .setStreet(address.street!!)
                    .setSuite(address.suite!!)
                    .setCity(address.city!!)
                    .setZipcode(address.zipcode!!)
                    .setGeo(buildOneGeoRealm(address.geo)))
        } else {
            AddressRealm()
        }
    }

    private fun buildOneGeoRealm(geo: Geo?): GeoRealm {
        return if (geo != null) {
            realm.copyToRealm(GeoRealm()
                    .setLat(geo.lat!!)
                    .setLng(geo.lng!!))
        } else {
            GeoRealm()
        }
    }

}
