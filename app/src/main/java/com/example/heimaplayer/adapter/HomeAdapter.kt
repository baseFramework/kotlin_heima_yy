package com.example.heimaplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.heimaplayer.widget.HomeItemView

class HomeAdapter:RecyclerView.Adapter<HomeAdapter.HomeHolder>(){

    class HomeHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun getItemCount(): Int {
        return  20
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(HomeItemView(parent?.context))
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {

    }

}