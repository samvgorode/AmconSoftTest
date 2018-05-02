package com.example.amconsofttest.ui.main.fragments.list;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.amconsofttest.R;
import com.example.amconsofttest.io.realm_models.ClientRealm;
import com.example.amconsofttest.io.rest.pojos.Client;
import com.example.amconsofttest.ui.main.MainActivity;
import com.example.amconsofttest.ui.main.fragments.OneClientFragment;

class SimpleViewHolder extends RecyclerView.ViewHolder {

    private TextView idText;
    private TextView nameText;
    private int id = 0;

    SimpleViewHolder(View view) {
        super(view);
        idText = itemView.findViewById(R.id.id_client);
        nameText = itemView.findViewById(R.id.client_name);
    }

    @SuppressLint ("SetTextI18n")
    void bindData(Object viewModel) {
        if (viewModel instanceof Client) {
            Client client = (Client) viewModel;
            id = client.getId();
            idText.setText(Integer.toString(id));
            nameText.setText(client.getName());
        }
        if (viewModel instanceof ClientRealm) {
            ClientRealm client = (ClientRealm) viewModel;
            id = client.getId();
            idText.setText(Integer.toString(id));
            nameText.setText(client.getName());
        }
        itemView.setOnClickListener(v -> {
            ((MainActivity) itemView.getContext())
                    .replaceFragment(R.id.fragment_holder, OneClientFragment.newInstance(id), "1");
        });
    }
}
