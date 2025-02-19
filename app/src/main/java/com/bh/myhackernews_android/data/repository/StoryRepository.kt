package com.bh.myhackernews_android.data.repository

import com.bh.myhackernews_android.data.api.HackerNewsService
import com.bh.myhackernews_android.data.model.Story

class StoryRepository(private val hackerNewsService: HackerNewsService) {

    suspend fun getNewStories(): List<Long> {
        return hackerNewsService.getNewStories()
    }

    suspend fun getStoryById(id: Long): Story? {
        return try {
            hackerNewsService.getStoryById(id)
        } catch (e: Exception) {
            null
        }
    }
}
