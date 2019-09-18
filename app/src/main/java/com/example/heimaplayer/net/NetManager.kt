package com.example.heimaplayer.net

import com.example.heimaplayer.util.ThreadUtil
import com.example.heimaplayer.util.URLProviderUtils
import com.google.gson.Gson
import com.itheima.player.model.bean.HomeBean
import com.itheima.player.model.bean.HomeItemBean
import okhttp3.*
import java.io.IOException

// 发送网络请求类
class NetManager private constructor(){
    val client by lazy { OkHttpClient() }
    companion object{
        val manager by lazy { NetManager() }
    }

    // 发送网络请求
    fun <RESPONSE>sendRequest(req:MRequest<RESPONSE>){
        val request = Request.Builder()
            .url(req.url)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {

            // 子线程调用
            override fun onFailure(call: Call, e: IOException) {
                println("获取数据失败:")
                //  myToast("获取数据失败")
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        req.handler.onError(req.type,e.message)
                    }
                })
            }

            //子线程调用
            override fun onResponse(call: Call, response: Response) {
                //myToast("获取数据成功")
                val result =  response.body()?.string()
                val parseResult = req.parseResult(result)

//                val gson = Gson()
//                val home = gson.fromJson(result, HomeBean::class.java)
//                val list = home.items
                //gson.fromJson<>()
//                val list = gson.fromJson<List<HomeItemBean>>(result,object: TypeToken<List<HomeItemBean>>(){}.type)
               // println("获取数据成功:" + list.size)

                // 刷新列表
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        req.handler.onSuccess(req.type,parseResult)
                        //homeView.loadMore(list)
                        //adapter.loadMore(list)
                    }
                })

            }
        })
    }
}