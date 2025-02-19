package com.bh.myhackernews_android.data.api

import com.bh.myhackernews_android.data.model.Story
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {
    @GET("newstories.json")
    suspend fun getNewStories(): List<Long>

    @GET("item/{id}.json")
    suspend fun getStoryById(@Path("id") id: Long): Story
}
