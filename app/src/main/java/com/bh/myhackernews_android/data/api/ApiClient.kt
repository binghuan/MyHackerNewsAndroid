package com.bh.myhackernews_android.data.api

import com.bh.myhackernews_android.data.model.Story
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {
    @GET("newstories.json?print=pretty")
    suspend fun getNewStories(): List<Long>

    @GET("item/{id}.json?print=pretty")
    suspend fun getStoryById(@Path("id") id: Long): Story
}

object ApiClient {
    private const val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

    val hackerNewsService: HackerNewsService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(HackerNewsService::class.java)
    }
}
