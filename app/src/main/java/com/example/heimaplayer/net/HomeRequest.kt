package com.example.heimaplayer.net

import com.example.heimaplayer.util.URLProviderUtils
import com.itheima.player.model.bean.HomeBean


// 首页数据请求类
class HomeRequest(type:Int,offset:Int,handler: ResponseHandler<HomeBean>) :MRequest<HomeBean>(type,URLProviderUtils.getHomeUrl(offset,20),handler){

}