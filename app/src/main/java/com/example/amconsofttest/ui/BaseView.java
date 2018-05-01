package com.example.amconsofttest.ui;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.example.amconsofttest.io.realm_models.ClientRealm;
import com.example.amconsofttest.io.rest.pojos.Client;

import java.util.List;

import io.realm.RealmList;

public interface BaseView extends MvpView {

    void showToast(@StringRes int id);

    default void newUsersaved() {}

    default void showProgressBar(){}

    default void hideProgressBar(){}

    default void setupListOfClients(List<Client> clients){}

    default void setupListOfClientsRealm(RealmList<ClientRealm> clientsRealm){}

    default void setupOneClientRealm(ClientRealm clientRealm){}

}
