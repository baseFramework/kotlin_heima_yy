package com.itheima.player.model.bean

data class HomeBean(
    val items: List<HomeItemBean>
)

data class Item(
    val clickUrl: String,
    val description: String,
    val hdUrl: String,
    val hdVideoSize: Int,
    val id: String,
    val posterPic: String,
    val status: Int,
    val thumbnaiPic: String,
    val title: String,
    val traceUrl: String,
    val type: String,
    val uhdUrl: String,
    val uhdVideoSize: Int,
    val url: String,
    val videoSize: Int
)