package com.example.heimaplayer.presenter.impl

import com.example.heimaplayer.net.HomeRequest
import com.example.heimaplayer.net.NetManager
import com.example.heimaplayer.net.ResponseHandler
import com.example.heimaplayer.presenter.interf.HomePresenter
import com.example.heimaplayer.ui.fragment.HomeFragment
import com.example.heimaplayer.util.ThreadUtil
import com.example.heimaplayer.util.URLProviderUtils
import com.example.heimaplayer.view.HomeView
import com.google.gson.Gson
import com.itheima.player.model.bean.HomeBean
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomePresenterImpl(var homeView: HomeView):HomePresenter, ResponseHandler<HomeBean> {

    //失败
    override fun onError(type:Int,msg: String?) {
       homeView.onError(msg)
    }

    // 加载数据成功
    override fun onSuccess(type:Int,result: HomeBean) {
        // 区分初始化  加载更多
        when(type){
            HomePresenter.TYPE_INIT_OR_REFRESH -> homeView.loadSuccess(result.items)
            HomePresenter.TYPE_LOAD_MORE -> homeView.loadMore(result.items)
        }
    }

    // 初始化数据 刷新数据
    override fun loadDatas() {
         // 定义request

        val request = HomeRequest(HomePresenter.TYPE_INIT_OR_REFRESH,0,this).excute()

    }

    override fun loadMore(offset: Int) {

        val request = HomeRequest(HomePresenter.TYPE_LOAD_MORE,offset,this).excute()


    }
}