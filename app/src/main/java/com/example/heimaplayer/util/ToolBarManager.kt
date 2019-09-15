package com.example.heimaplayer.util

import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.heimaplayer.R
import com.example.heimaplayer.ui.activity.SettingAcitivity


interface ToolBarManager{
    val toolbar: Toolbar


    // 初始化toolbar 管理类
    fun initMainToolBar(){
        toolbar.setTitle("黑马影音")
        toolbar.inflateMenu(R.menu.main)

        // kotlin 和 java调用特性
        // 如果java 接口中，只有一个未实现的方法 可以省略接口的对象，直接用{}表示方法
        toolbar.setOnMenuItemClickListener{
            println("item=$it")
            toolbar.context.startActivity(Intent(toolbar.context,SettingAcitivity::class.java))
            true
        }

//        toolbar.setOnMenuItemClickListener(object:Toolbar.OnMenuItemClickListener{
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                when (item?.itemId){
//                    R.id.setting->{
//                       // Toast.makeText(toolbar.context,"点击了设置按钮",Toast.LENGTH_SHORT).show();
//
//                        // 跳转到设置界面
//                        toolbar.context.startActivity(Intent(toolbar.context,SettingAcitivity::class.java))
//                    }
//                }
//                return true
//            }
//        })
    }


    // 处理设置界面的toolbar
    fun initSettingToolBar(){
        toolbar.setTitle("设置界面")
    }

}