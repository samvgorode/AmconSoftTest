package com.example.amconsofttest.ui.main.fragments.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.amconsofttest.R

class RecyclerAdapter(listItems: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var models: List<Any> = listItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SimpleViewHolder).bindData(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_client
    }
}
