package com.example.heimaplayer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


abstract class BaseActivity:AppCompatActivity(),AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initDate()
//        debug { "哈哈" }
    }


    // 初始化数据
    open protected fun initDate(){

    }

    // adapter listener
    open protected fun initListener(){

    }


    // 获取布局ID
    abstract fun getLayoutId():Int

    // 弹出提示
    open protected  fun myToast(msg:String){
        runOnUiThread { toast(msg) }
    }

    //开启 activity并且finish 当前界面
    inline fun<reified T:BaseActivity> startActivityAndFinish(){
        startActivity<T>()
        finish()
    }


}