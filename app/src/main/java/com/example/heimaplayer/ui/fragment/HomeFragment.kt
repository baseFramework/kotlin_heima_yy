package com.example.heimaplayer.ui.fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.heimaplayer.R
import com.example.heimaplayer.adapter.HomeAdapter
import com.example.heimaplayer.base.BaseFragment
import com.example.heimaplayer.util.ThreadUtil
import com.example.heimaplayer.util.URLProviderUtils
import com.google.gson.Gson
import  com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeBean
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class HomeFragment:BaseFragment() {

    //适配
    val adapter by lazy { HomeAdapter() }
    override fun initView(): View? {
      return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener(){

        //初始化recycleview
        recycleView.layoutManager = LinearLayoutManager(context)


        recycleView.adapter = adapter

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
                            loadMore(adapter.itemCount-1)
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
        loadDatas()
    }

    // 加载更多数据
    private  fun loadMore(offset:Int){
        val path = URLProviderUtils.getHomeUrl(offset,20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object :Callback{

            // 子线程调用
            override fun onFailure(call: Call, e: IOException) {
                println("获取数据失败:")
                println(e)
                myToast("获取数据失败")

            }

            //子线程调用
            override fun onResponse(call: Call, response: Response) {
                myToast("获取数据成功")
                val result =  response.body()?.string()
                val gson = Gson()
                val home = gson.fromJson(result, HomeBean::class.java)
                val list = home.items
                //gson.fromJson<>()
//                val list = gson.fromJson<List<HomeItemBean>>(result,object: TypeToken<List<HomeItemBean>>(){}.type)
                println("获取数据成功:" + list.size)

                // 刷新列表
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        adapter.loadMore(list)
                    }
                })

            }
        })
    }

    private  fun loadDatas(){
        val path = URLProviderUtils.getHomeUrl(0,20)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object :Callback{

            // 子线程调用
            override fun onFailure(call: Call, e: IOException) {
                println("获取数据失败:")
                println(e)
                myToast("获取数据失败")

            }

            //子线程调用
            override fun onResponse(call: Call, response: Response) {
                myToast("获取数据成功")
                val result =  response.body()?.string()
                val gson = Gson()
                val home = gson.fromJson(result, HomeBean::class.java)
                val list = home.items
                //gson.fromJson<>()
//                val list = gson.fromJson<List<HomeItemBean>>(result,object: TypeToken<List<HomeItemBean>>(){}.type)
                println("获取数据成功:" + list.size)

                // 刷新列表
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        adapter.updateList(list)
                    }
                })

            }
        })
    }
}