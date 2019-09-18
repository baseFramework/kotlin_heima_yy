package com.example.heimaplayer.net

// 请求回调
interface ResponseHandler<RESPONSE> {
    fun onError(type:Int,msg:String?)
    fun onSuccess(type:Int,result:RESPONSE)
}