package com.example.heimaplayer.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.heimaplayer.widget.YueDanItemView

class YueDanAdapter: RecyclerView.Adapter<YueDanAdapter.YueDanHolder>(){
    class YueDanHolder(itemView: YueDanItemView):RecyclerView.ViewHolder(itemView){

    }

    override fun getItemCount(): Int {
        return  20
    }

    override fun onBindViewHolder(holder: YueDanHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YueDanHolder {
        return YueDanHolder(YueDanItemView(parent?.context))
    }
}