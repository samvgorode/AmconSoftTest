package com.example.amconsofttest.ui.main.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.example.amconsofttest.R;
import com.example.amconsofttest.io.realm_models.AddressRealm;
import com.example.amconsofttest.io.realm_models.ClientRealm;
import com.example.amconsofttest.io.realm_models.CompanyRealm;
import com.example.amconsofttest.ui.base.BaseFragment;
import com.example.amconsofttest.ui.main.MainPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneClientFragment extends BaseFragment implements OnMapReadyCallback {

    @BindView(R.id.user_id)
    TextView userId;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_username)
    TextView userUserName;
    @BindView(R.id.user_email)
    TextView userEmail;
    @BindView(R.id.user_address)
    TextView userAddress;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.user_website)
    TextView userWebsite;
    @BindView(R.id.user_company)
    TextView userCompany;
    @BindView(R.id.map_view)
    com.google.android.gms.maps.MapView mapView;


    private int clientId;
    private static String ID = "ID";
    GoogleMap map;
    String lat = "";
    String lon = "";
    String clientName = "";

    @InjectPresenter
    MainPresenter presenter;

    public static OneClientFragment newInstance(int clientId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID, clientId);
        OneClientFragment fragment = new OneClientFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_client, container, false);
        ButterKnife.bind(this, view);
        mapView.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null) presenter.getOneClientById(getArguments().getInt(ID));
    }

    @SuppressLint ("SetTextI18n")
    @Override
    public void setupOneClientRealm(ClientRealm clientRealm) {
        if(clientRealm!=null && clientRealm.isValid()){
            userId.setText(Integer.toString(clientRealm.getId()));
            clientName = clientRealm.getName();
            userName.setText(clientName);
            userUserName.setText(clientRealm.getUsername());
            userEmail.setText(clientRealm.getEmail());
            AddressRealm addressRealm = clientRealm.getAddress();
            userAddress.setText(TextUtils.concat(addressRealm.getZipcode()," ",
                    addressRealm.getCity()," ",addressRealm.getStreet()," ",addressRealm.getSuite()));
            userPhone.setText(clientRealm.getPhone());
            userWebsite.setText(clientRealm.getWebsite());
            CompanyRealm companyRealm = clientRealm.getCompany();
            userCompany.setText(TextUtils.concat(companyRealm.getName()," ",
                    companyRealm.getCatchPhrase()," ", companyRealm.getBs()));

            lat = addressRealm.getGeo().getLat();
            lon = addressRealm.getGeo().getLng();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        LatLng client = new LatLng(Double.valueOf(lat), Double.valueOf(lon));
        map.moveCamera(CameraUpdateFactory.newLatLng(client));
        map.addMarker(new MarkerOptions()
                .position(client)
                .title(clientName));
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        map.clear();
        mapView.onDestroy();
        mapView = null;
        super.onDestroyView();
    }
}
