package com.example.amconsofttest.ui.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.amconsofttest.App;
import com.example.amconsofttest.io.ApiFactory;
import com.example.amconsofttest.io.builders.ClientBuilder;
import com.example.amconsofttest.io.realm_models.AddressRealm;
import com.example.amconsofttest.io.realm_models.ClientRealm;
import com.example.amconsofttest.io.realm_models.CompanyRealm;
import com.example.amconsofttest.io.realm_models.GeoRealm;
import com.example.amconsofttest.io.realm_models.UserRealm;
import com.example.amconsofttest.io.rest.pojos.Client;
import com.example.amconsofttest.ui.BaseView;
import com.facebook.login.LoginManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

@InjectViewState
public class MainPresenter extends MvpPresenter<BaseView> {

    private Realm realm;

    public MainPresenter() {
        realm = App.getRealm();
    }

    String getCurrentUserId() {
        UserRealm userRealm = realm.where(UserRealm.class).findFirst();
        return userRealm != null ? userRealm.getId() : "";
    }

    String getCurrentUserName() {
        UserRealm userRealm = realm.where(UserRealm.class).findFirst();
        return userRealm != null ? userRealm.getName() : "";
    }

    String getCurrentUserEmail() {
        UserRealm userRealm = realm.where(UserRealm.class).findFirst();
        return userRealm != null ? userRealm.getEmail() : "";
    }

    void logoutUser() {
        LoginManager.getInstance().logOut();
        realm.beginTransaction();
        realm.delete(UserRealm.class);
        realm.commitTransaction();
    }

    public void getClientsAndSave() {
        ApiFactory.getApiService().getClients()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getViewState().showProgressBar())
                .doAfterTerminate(() -> getViewState().hideProgressBar())
                .subscribe(this::setupSimpleList,
                        throwable -> setupRealmList());
    }

    private void setupSimpleList(List<Client> clients){
        realm.beginTransaction();
        realm.delete(ClientRealm.class);
        realm.delete(AddressRealm.class);
        realm.delete(CompanyRealm.class);
        realm.delete(GeoRealm.class);
        realm.commitTransaction();
        ClientBuilder.build(clients);
        getViewState().setupListOfClients(clients);
    }

    private void setupRealmList(){
        RealmResults<ClientRealm> realmResults = App.getRealm().where(ClientRealm.class).findAll();
        RealmList<ClientRealm> realmList = new RealmList<>();
        realmList.addAll(realmResults);
        if(realmList.isValid() && realmList.size() > 0)
        getViewState().setupListOfClientsRealm(realmList);
    }

    public void getOneClientById(int id) {
        getViewState().setupOneClientRealm(realm.where(ClientRealm.class)
                .equalTo("id", id).findFirst());
    }
}
