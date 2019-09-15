package com.example.heimaplayer.ui.activity

import android.preference.PreferenceManager
import androidx.appcompat.widget.Toolbar
import com.example.heimaplayer.R
import com.example.heimaplayer.base.BaseActivity
import com.example.heimaplayer.util.ToolBarManager
import org.jetbrains.anko.find

class SettingAcitivity:BaseActivity(),ToolBarManager{

    // 惰性加载toolbar 方式
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initDate() {
       initSettingToolBar()

        // 获取推送通知有没有选中
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val push = sp.getBoolean("push",false)
        println(push);
    }

}