package com.example.amconsofttest.ui.main.fragments.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.amconsofttest.R
import com.example.amconsofttest.io.realm_models.ClientRealm
import com.example.amconsofttest.io.rest.pojos.Client
import com.example.amconsofttest.ui.base.BaseFragment
import com.example.amconsofttest.ui.main.MainPresenter
import io.realm.RealmList
import kotlinx.android.synthetic.main.fragment_user_list.*

class ClientListFragment : BaseFragment() {

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    private var adapter: RecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.setHasFixedSize(true)
        list.layoutManager = LinearLayoutManager(context)
        presenter.getClientsAndSave()
    }

    override fun showToast(id: Int) {}

    override fun setupListOfClients(clients: List<Client>) {
        adapter = RecyclerAdapter(clients)
        list.adapter = adapter
    }

    override fun setupListOfClientsRealm(clientsRealm: RealmList<ClientRealm>) {
        adapter = RecyclerAdapter(clientsRealm)
        list.adapter = adapter
    }

    companion object {
        fun newInstance(): ClientListFragment {
            return ClientListFragment()
        }
    }
}
