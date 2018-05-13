package com.example.amconsofttest.ui.main.fragments.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.amconsofttest.R
import com.example.amconsofttest.io.realm_models.ClientRealm
import com.example.amconsofttest.io.rest.pojos.Client
import com.example.amconsofttest.ui.base.BaseFragment
import com.example.amconsofttest.ui.main.MainPresenter

import butterknife.BindView
import butterknife.ButterKnife
import io.realm.RealmList

class ClientListFragment : BaseFragment() {

    @BindView(R.id.list) lateinit var recyclerView: RecyclerView

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    private var adapter: RecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        presenter.getClientsAndSave()
    }

    override fun showToast(id: Int) {}

    override fun setupListOfClients(clients: List<Client>) {
        adapter = RecyclerAdapter(clients)
        recyclerView.adapter = adapter
    }

    override fun setupListOfClientsRealm(clientsRealm: RealmList<ClientRealm>) {
        adapter = RecyclerAdapter(clientsRealm)
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): ClientListFragment {
            return ClientListFragment()
        }
    }
}
