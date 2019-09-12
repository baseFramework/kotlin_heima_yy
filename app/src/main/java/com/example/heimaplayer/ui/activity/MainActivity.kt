package com.example.heimaplayer.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.heimaplayer.R
import com.example.heimaplayer.base.BaseActivity
import com.example.heimaplayer.util.ToolBarManager
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolBarManager {

    // 惰性加载toolbar 方式
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initDate() {
        initMainToolBar()
    }

}
