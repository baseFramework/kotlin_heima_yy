package com.example.heimaplayer.net

import com.google.gson.Gson
import com.itheima.player.model.bean.HomeBean
import java.lang.reflect.ParameterizedType

// 所有请求的基类
open class MRequest<RESPONSE>(val type:Int,val url:String,val handler: ResponseHandler<RESPONSE>) {
    //解析网络请求结果
    fun parseResult(result: String?): RESPONSE {
        val gson = Gson()
        // 获取泛型类型
        val type = (this.javaClass.genericSuperclass as ParameterizedType).getActualTypeArguments()[0]
        val resObj = gson.fromJson<RESPONSE>(result, type)
        //val list = home.items
        return resObj
    }


    // 发送网络请求
    fun excute(){
        NetManager.manager.sendRequest(this)
    }
}