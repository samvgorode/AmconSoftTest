package com.example.amconsofttest.ui.main.fragments.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.amconsofttest.R;
import com.example.amconsofttest.io.realm_models.ClientRealm;
import com.example.amconsofttest.io.rest.pojos.Client;
import com.example.amconsofttest.ui.base.BaseFragment;
import com.example.amconsofttest.ui.main.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class ClientListFragment extends BaseFragment {

    @BindView (R.id.list)
    RecyclerView recyclerView;
    @InjectPresenter
    MainPresenter presenter;

    private RecyclerAdapter adapter;

    public ClientListFragment() {
    }

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.getClientsAndSave();
    }

    @Override
    public void showToast(int id) {}

    @Override
    public void setupListOfClients(List<Client> clients) {
        adapter = new RecyclerAdapter(clients);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setupListOfClientsRealm(RealmList<ClientRealm> clientsRealm) {
        adapter = new RecyclerAdapter(clientsRealm);
        recyclerView.setAdapter(adapter);
    }
}
