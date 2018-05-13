package com.example.amconsofttest.ui.main.fragments.list

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.amconsofttest.R
import com.example.amconsofttest.io.realm_models.ClientRealm
import com.example.amconsofttest.io.rest.pojos.Client
import com.example.amconsofttest.ui.main.MainActivity
import com.example.amconsofttest.ui.main.fragments.OneClientFragment

internal class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val idText: TextView
    private val nameText: TextView
    private var id = 0

    init {
        idText = itemView.findViewById(R.id.id_client)
        nameText = itemView.findViewById(R.id.client_name)
    }

    @SuppressLint("SetTextI18n")
    fun bindData(viewModel: Any) {
        if (viewModel is Client) {
            id = viewModel.id!!
            idText.text = Integer.toString(id)
            nameText.text = viewModel.name
        }
        if (viewModel is ClientRealm) {
            id = viewModel.getId()!!
            idText.text = Integer.toString(id)
            nameText.text = viewModel.getName()
        }
        itemView.setOnClickListener {
            (itemView.context as MainActivity)
                    .replaceFragment(R.id.fragment_holder, OneClientFragment.newInstance(id), "1")
        }
    }
}
