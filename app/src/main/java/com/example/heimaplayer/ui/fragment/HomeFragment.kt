package com.example.heimaplayer.ui.fragment
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heimaplayer.R
import com.example.heimaplayer.adapter.HomeAdapter
import com.example.heimaplayer.base.BaseFragment
import com.example.heimaplayer.presenter.impl.HomePresenterImpl
import com.example.heimaplayer.view.HomeView
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*
class HomeFragment:BaseFragment(),HomeView {

    //适配
    val adapter by lazy { HomeAdapter() }
    val presenter by lazy { HomePresenterImpl(this) }
    override fun initView(): View? {
      return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener(){

        //初始化recycleview
        recycleView.layoutManager = LinearLayoutManager(context)


        recycleView.adapter = adapter

        // 初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.BLUE)


        refreshLayout.setOnRefreshListener {
            // 刷新监听
           // loadDatas()
            presenter.loadDatas()
        }

        // 监听列表滑动
        recycleView.addOnScrollListener(object:RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//               when(newState){
//                   RecyclerView.SCROLL_STATE_IDLE->{
//                       println("idel")
//                   }
//                   RecyclerView.SCROLL_STATE_DRAGGING->{
//                       println("drag")
//                   }
//                   RecyclerView.SCROLL_STATE_SETTLING->{
//                       println("set")
//                   }
//               }

                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    // 是否最后一条显示
                    val layoutManager = recyclerView.layoutManager
                    if(layoutManager is LinearLayoutManager){
                        val manager:LinearLayoutManager = layoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if(lastPosition == adapter.itemCount -1){
                            //最后一条已经显示了
                            presenter.loadMore(adapter.itemCount-1)
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                println("on scrolled dx=$dx dy =$dy")
            }
        })
    }

    override  fun initData(){
        // 初始化数据
        // loadDatas();
       presenter.loadDatas()
    }

    override fun onError(message: String?) {
       myToast("加载数据失败")
    }

    override fun loadSuccess(list: List<HomeItemBean>) {
        myToast("加载数据成功")
        //隐藏刷新控件
        refreshLayout.isRefreshing = false;
        //刷新列表
        adapter.updateList(list)
    }

    override fun loadMore(list: List<HomeItemBean>) {
       adapter.loadMore(list)
    }
}