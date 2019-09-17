package com.example.heimaplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.heimaplayer.widget.HomeItemView
import com.example.heimaplayer.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean

class HomeAdapter:RecyclerView.Adapter<HomeAdapter.HomeHolder>(){

    private var list = ArrayList<HomeItemBean>()

    // 更新数据
    fun updateList(list: List<HomeItemBean>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    // 加载更多数据
    fun loadMore(list:List<HomeItemBean>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class HomeHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun getItemViewType(position: Int): Int {
        if(position == list.size){
            return 1
        }else{
            return 0
        }
    }


    override fun getItemCount(): Int {
        return  list.size + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        if(viewType ==1){
            // 最后一条
            return HomeHolder(LoadMoreView(parent.context))
        }else{
            // 普通条目
            return HomeHolder(HomeItemView(parent.context))
        }

    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {

        // 如果是最后一条 就不需要再来刷新view
        if(position == list.size) return

        // 条目数据
        val data = list.get(position)

        // 条目view
        val itemView = holder.itemView as HomeItemView

        // 条目刷新
        itemView.setData(data)
    }

}