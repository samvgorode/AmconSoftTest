package com.example.amconsofttest.ui

import android.support.annotation.StringRes

import com.arellomobile.mvp.MvpView
import com.example.amconsofttest.io.realm_models.ClientRealm
import com.example.amconsofttest.io.rest.pojos.Client

import io.realm.RealmList

interface BaseView : MvpView {

    fun showToast(@StringRes id: Int)

    fun newUsersaved() {}

    fun showProgressBar() {}

    fun hideProgressBar() {}

    fun setupListOfClients(clients: List<Client>) {}

    fun setupListOfClientsRealm(clientsRealm: RealmList<ClientRealm>) {}

    fun setupOneClientRealm(clientRealm: ClientRealm) {}

}
