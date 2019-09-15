package com.example.heimaplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heimaplayer.R
import com.example.heimaplayer.adapter.HomeAdapter
import com.example.heimaplayer.base.BaseFragment
import com.example.heimaplayer.util.URLProviderUtils
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomeFragment:BaseFragment() {
    override fun initView(): View? {
      return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener(){

        //初始化recycleview
        recycleView.layoutManager = LinearLayoutManager(context)

        //适配
        val adapter = HomeAdapter()
        recycleView.adapter = adapter
    }

    override  fun initData(){
        loadDatas()
    }

    private  fun loadDatas(){
        val path = URLProviderUtils.getHomeUrl(0,20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object :Callback{

            // 子线程调用
            override fun onFailure(call: Call, e: IOException) {
                println("获取数据失败:")
                myToast("获取数据失败")

            }

            //子线程调用
            override fun onResponse(call: Call, response: Response) {
                myToast("获取数据成功")
                val result =  response.body()?.string()
                println("获取数据成功:" + result)
            }
        })
    }
}