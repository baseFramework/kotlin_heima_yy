package com.example.heimaplayer.view

import com.itheima.player.model.bean.HomeItemBean

// home 界面和presenter 交互
interface HomeView {

    // 获取数据失败
    fun onError(message: String?) {

    }

    // 初始化数据 刷新数据成功
    fun loadSuccess(list: List<HomeItemBean>) {

    }

    // 加载更多数据成功
    fun loadMore(list: List<HomeItemBean>) {

    }

}