package com.example.amconsofttest.io.builders;

import com.example.amconsofttest.App;
import com.example.amconsofttest.io.realm_models.AddressRealm;
import com.example.amconsofttest.io.realm_models.ClientRealm;
import com.example.amconsofttest.io.realm_models.CompanyRealm;
import com.example.amconsofttest.io.realm_models.GeoRealm;
import com.example.amconsofttest.io.rest.pojos.Address;
import com.example.amconsofttest.io.rest.pojos.Client;
import com.example.amconsofttest.io.rest.pojos.Company;
import com.example.amconsofttest.io.rest.pojos.Geo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class ClientBuilder {

    private static Realm getRealm(){
        return App.getRealm();
    }

    public static RealmList<ClientRealm> build(List<Client> clients) {
        RealmList<ClientRealm> realmList = new RealmList<>();
        for (Client client : clients) {
            if (client != null) {
                ClientRealm clientRealm = buildOneClientRealm(client);
                realmList.add(clientRealm);
            }
        }
        return realmList;
    }

    private static ClientRealm buildOneClientRealm(Client client) {
        if (client != null) {
            ClientRealm clientRealm;
            getRealm().beginTransaction();
            clientRealm = getRealm().copyToRealmOrUpdate(new ClientRealm()
                    .setId(client.getId())
                    .setName(client.getName())
                    .setUsername(client.getUsername())
                    .setEmail(client.getEmail())
                    .setAddress(buildOneAddressRealm(client.getAddress()))
                    .setPhone(client.getPhone())
                    .setWebsite(client.getWebsite())
                    .setCompany(buildOneCompanyRealm(client.getCompany())));
            getRealm().commitTransaction();
            return clientRealm;
        } else {return new ClientRealm();}

    }

    private static CompanyRealm buildOneCompanyRealm(Company company) {
        if (company != null) {
            return getRealm().copyToRealmOrUpdate(new CompanyRealm()
                    .setName(company.getName())
                    .setCatchPhrase(company.getCatchPhrase())
                    .setBs(company.getBs()));
        } else {
            return new CompanyRealm();
        }
    }

    private static AddressRealm buildOneAddressRealm(Address address) {
        if (address != null) {
            return getRealm().copyToRealm(new AddressRealm()
                    .setStreet(address.getStreet())
                    .setSuite(address.getSuite())
                    .setCity(address.getCity())
                    .setZipcode(address.getZipcode())
                    .setGeo(buildOneGeoRealm(address.getGeo())));
        } else {
            return new AddressRealm();
        }
    }

    private static GeoRealm buildOneGeoRealm(Geo geo) {
        if (geo != null) {
            return getRealm().copyToRealm(new GeoRealm()
                    .setLat(geo.getLat())
                    .setLng(geo.getLng()));
        } else {
            return new GeoRealm();
        }
    }

}
