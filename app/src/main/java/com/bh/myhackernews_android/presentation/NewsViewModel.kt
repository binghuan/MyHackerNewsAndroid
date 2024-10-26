package com.bh.myhackernews_android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bh.myhackernews_android.data.api.ApiClient
import com.bh.myhackernews_android.data.model.Story
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class NewsViewModel : ViewModel() {
    private val _stories = MutableStateFlow<List<Story>>(emptyList())
    open val stories: StateFlow<List<Story>> = _stories

    private val _isLoading = MutableStateFlow(true)
    open val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadStories()
    }

    private fun loadStories() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val storyIds =
                    ApiClient.hackerNewsService.getNewStories().take(10)
                val fetchedStories = storyIds.mapNotNull { id ->
                    try {
                        ApiClient.hackerNewsService.getStoryById(id)
                    } catch (e: Exception) {
                        null
                    }
                }
                _stories.value = fetchedStories.sortedByDescending { it.score }
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}

