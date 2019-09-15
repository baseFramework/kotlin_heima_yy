package com.example.heimaplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.heimaplayer.base.BaseFragment

class MvFragment:BaseFragment() {
    override fun initView(): View? {
        val mv = TextView(context)
        mv.gravity = Gravity.CENTER
        mv.setTextColor(Color.RED)
        mv.text = javaClass.simpleName
        return mv
    }
}