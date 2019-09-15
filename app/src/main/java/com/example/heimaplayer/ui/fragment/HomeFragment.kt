package com.example.heimaplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heimaplayer.R
import com.example.heimaplayer.adapter.HomeAdapter
import com.example.heimaplayer.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

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
}