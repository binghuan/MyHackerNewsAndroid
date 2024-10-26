package com.bh.myhackernews_android.data.model

data class Story(
    val id: Long,
    val by: String,
    val descendants: Int,
    val score: Int,
    val time: Long,
    val title: String,
    val type: String,
    val url: String?
)
