package com.example.amconsofttest.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.amconsofttest.App
import com.example.amconsofttest.io.ApiFactory
import com.example.amconsofttest.io.builders.ClientBuilder
import com.example.amconsofttest.io.realm_models.AddressRealm
import com.example.amconsofttest.io.realm_models.ClientRealm
import com.example.amconsofttest.io.realm_models.CompanyRealm
import com.example.amconsofttest.io.realm_models.GeoRealm
import com.example.amconsofttest.io.realm_models.UserRealm
import com.example.amconsofttest.io.rest.pojos.Client
import com.example.amconsofttest.ui.BaseView
import com.facebook.login.LoginManager

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults

@InjectViewState
class MainPresenter : MvpPresenter<BaseView>() {

    private val realm: Realm = App.realm

    internal val currentUserId: String? = realm.where(UserRealm::class.java).findFirst()?.id

    internal val currentUserName: String?
        get() {
            val userRealm = realm.where(UserRealm::class.java).findFirst()
            return if (userRealm != null) userRealm.name else ""
        }

    internal val currentUserEmail: String?
        get() {
            val userRealm = realm.where(UserRealm::class.java).findFirst()
            return if (userRealm != null) userRealm.email else ""
        }

    internal fun logoutUser() {
        LoginManager.getInstance().logOut()
        realm.beginTransaction()
        realm.delete(UserRealm::class.java)
        realm.commitTransaction()
    }

    fun getClientsAndSave() {
        ApiFactory.apiService.clients
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showProgressBar() }
                .doAfterTerminate { viewState.hideProgressBar() }
                .subscribe({ clients ->  setupSimpleList(clients)},
                        { setupRealmList() })
    }

    private fun setupSimpleList(clients: List<Client>) {
        realm.beginTransaction()
        realm.delete(ClientRealm::class.java)
        realm.delete(AddressRealm::class.java)
        realm.delete(CompanyRealm::class.java)
        realm.delete(GeoRealm::class.java)
        realm.commitTransaction()
        ClientBuilder.build(clients)
        viewState.setupListOfClients(clients)
    }

    private fun setupRealmList() {
        val realmResults = App.realm!!.where(ClientRealm::class.java).findAll()
        val realmList = RealmList<ClientRealm>()
        realmList.addAll(realmResults)
        if (realmList.isValid && realmList.size > 0)
            viewState.setupListOfClientsRealm(realmList)
    }

    fun getOneClientById(id: Int) {
        viewState.setupOneClientRealm(realm.where(ClientRealm::class.java)
                .equalTo("id", id).findFirst()!!)
    }
}
