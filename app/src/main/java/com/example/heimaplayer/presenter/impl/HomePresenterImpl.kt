package com.example.heimaplayer.presenter.impl

import com.example.heimaplayer.presenter.interf.HomePresenter
import com.example.heimaplayer.ui.fragment.HomeFragment
import com.example.heimaplayer.util.ThreadUtil
import com.example.heimaplayer.util.URLProviderUtils
import com.example.heimaplayer.view.HomeView
import com.google.gson.Gson
import com.itheima.player.model.bean.HomeBean
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomePresenterImpl(var homeView: HomeView):HomePresenter {

    // 初始化数据 刷新数据
    override fun loadDatas() {
        val path = URLProviderUtils.getHomeUrl(0,20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object :Callback{

            // 子线程调用
            override fun onFailure(call: Call, e: IOException) {
                //隐藏刷新控件
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //refreshLayout.isRefreshing = false

                        // 回调到view层处理
                        homeView.onError(e?.message)
                    }
                })
                println("获取数据失败:")
                //myToast("获取数据失败")

            }

            //子线程调用
            override fun onResponse(call: Call, response: Response) {
                //隐藏刷新控件
               // refreshLayout.isRefreshing = false;
                //myToast("获取数据成功")
                val result =  response.body()?.string()
                val gson = Gson()
                val home = gson.fromJson(result, HomeBean::class.java)
                val list = home.items
                //gson.fromJson<>()
//                val list = gson.fromJson<List<HomeItemBean>>(result,object: TypeToken<List<HomeItemBean>>(){}.type)
                println("获取数据成功:" + list.size)

                // 刷新列表
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {

                        // 将正确结果回调到view层
                        homeView.loadSuccess(list)
                        //refreshLayout.isRefreshing = false
                        //adapter.updateList(list)
                    }
                })

            }
        })
    }

    override fun loadMore(offset: Int) {
        val path = URLProviderUtils.getHomeUrl(offset,20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {

            // 子线程调用
            override fun onFailure(call: Call, e: IOException) {

                println("获取数据失败:")
               //  myToast("获取数据失败")
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //refreshLayout.isRefreshing = false

                        // 回调到view层处理
                        homeView.onError(e?.message)
                    }
                })

            }

            //子线程调用
            override fun onResponse(call: Call, response: Response) {

                //myToast("获取数据成功")
                val result =  response.body()?.string()
                val gson = Gson()
                val home = gson.fromJson(result, HomeBean::class.java)
                val list = home.items
                //gson.fromJson<>()
//                val list = gson.fromJson<List<HomeItemBean>>(result,object: TypeToken<List<HomeItemBean>>(){}.type)
                println("获取数据成功:" + list.size)

                // 刷新列表
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        homeView.loadMore(list)
                        //adapter.loadMore(list)
                    }
                })

            }
        })
    }
}