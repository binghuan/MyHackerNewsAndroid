package com.bh.myhackernews_android.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HackerNewsApiClient {
    private const val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val hackerNewsService: HackerNewsService = retrofit.create(HackerNewsService::class.java)
}
