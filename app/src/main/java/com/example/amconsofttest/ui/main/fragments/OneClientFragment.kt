package com.example.amconsofttest.ui.main.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
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

class OneClientFragment : BaseFragment(), OnMapReadyCallback {

    @BindView(R.id.user_id)
    lateinit var userId: TextView
    @BindView(R.id.user_name)
    lateinit var userName: TextView
    @BindView(R.id.user_username)
    lateinit var userUserName: TextView
    @BindView(R.id.user_email)
    lateinit var userEmail: TextView
    @BindView(R.id.user_address)
    lateinit var userAddress: TextView
    @BindView(R.id.user_phone)
    lateinit var userPhone: TextView
    @BindView(R.id.user_website)
    lateinit var userWebsite: TextView
    @BindView(R.id.user_company)
    lateinit var userCompany: TextView
    @BindView(R.id.map_view)
    lateinit var mapView: com.google.android.gms.maps.MapView

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
        ButterKnife.bind(this, view)
        mapView.onCreate(savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) presenter.getOneClientById(arguments!!.getInt(ID))
    }

    @SuppressLint("SetTextI18n")
    override fun setupOneClientRealm(clientRealm: ClientRealm) {
        if (clientRealm.isValid) {
            userId.text = Integer.toString(clientRealm.getId()!!)
            clientName = clientRealm.getName()
            userName.text = clientName
            userUserName.text = clientRealm.getUsername()
            userEmail.text = clientRealm.getEmail()
            val addressRealm = clientRealm.getAddress()
            userAddress.text = TextUtils.concat(addressRealm!!.getZipcode(), " ",
                    addressRealm.getCity(), " ", addressRealm.getStreet(), " ", addressRealm.getSuite())
            userPhone.text = clientRealm.getPhone()
            userWebsite.text = clientRealm.getWebsite()
            val companyRealm = clientRealm.getCompany()
            userCompany.text = TextUtils.concat(companyRealm!!.getName(), " ",
                    companyRealm.getCatchPhrase(), " ", companyRealm.getBs())

            lat = addressRealm.getGeo()!!.getLat()
            lon = addressRealm.getGeo()!!.getLng()
            mapView.getMapAsync(this)
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
        mapView.onResume()
        super.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        map!!.clear()
        mapView.onDestroy()
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
