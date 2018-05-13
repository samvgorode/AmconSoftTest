package com.example.amconsofttest.ui.main.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.amconsofttest.R
import com.example.amconsofttest.io.realm_models.ClientRealm
import com.example.amconsofttest.ui.base.BaseFragment
import com.example.amconsofttest.ui.main.MainPresenter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_one_client.*

class OneClientFragment : BaseFragment(), OnMapReadyCallback {

    private val clientId: Int = 0
    internal var map: GoogleMap? = null
    internal var lat: String? = ""
    internal var lon: String? = ""
    internal var clientName: String? = ""

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_one_client, container, false)
        map_view.onCreate(savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) presenter.getOneClientById(arguments!!.getInt(ID))
    }

    @SuppressLint("SetTextI18n")
    override fun setupOneClientRealm(clientRealm: ClientRealm) {
        if (clientRealm.isValid) {
            user_id.text = Integer.toString(clientRealm.getId()!!)
            clientName = clientRealm.getName()
            user_name.text = clientName
            user_username.text = clientRealm.getUsername()
            user_email.text = clientRealm.getEmail()
            val addressRealm = clientRealm.getAddress()
            user_address.text = TextUtils.concat(addressRealm!!.getZipcode(), " ",
                    addressRealm.getCity(), " ", addressRealm.getStreet(), " ", addressRealm.getSuite())
            user_phone.text = clientRealm.getPhone()
            user_website.text = clientRealm.getWebsite()
            val companyRealm = clientRealm.getCompany()
            user_company.text = TextUtils.concat(companyRealm!!.getName(), " ",
                    companyRealm.getCatchPhrase(), " ", companyRealm.getBs())

            lat = addressRealm.getGeo()!!.getLat()
            lon = addressRealm.getGeo()!!.getLng()
            map_view.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map!!.uiSettings.isMyLocationButtonEnabled = false
        val client = LatLng(java.lang.Double.valueOf(lat)!!, java.lang.Double.valueOf(lon)!!)
        map!!.moveCamera(CameraUpdateFactory.newLatLng(client))
        map!!.addMarker(MarkerOptions()
                .position(client)
                .title(clientName))
    }

    override fun onResume() {
        map_view.onResume()
        super.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map_view.onLowMemory()
    }

    override fun onDestroyView() {
        map!!.clear()
        map_view.onDestroy()
        super.onDestroyView()
    }

    companion object {
        private val ID = "ID"

        fun newInstance(clientId: Int): OneClientFragment {
            val bundle = Bundle()
            bundle.putInt(ID, clientId)
            val fragment = OneClientFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
