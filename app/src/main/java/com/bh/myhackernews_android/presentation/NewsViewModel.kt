package com.bh.myhackernews_android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bh.myhackernews_android.data.model.Story
import com.bh.myhackernews_android.data.repository.StoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class NewsViewModel(private val repository: StoryRepository) : ViewModel() {
    private val _stories = MutableStateFlow<List<Story>>(emptyList())
    open val stories: StateFlow<List<Story>> = _stories

    private val _isLoading = MutableStateFlow(true)
    open val isLoading: StateFlow<Boolean> = _isLoading

    private var currentPage = 0
    private val pageSize = 10

    init {
        loadStories()
    }

    private fun loadStories() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val storyIds = repository.getNewStories().drop(currentPage * pageSize).take(pageSize)
                val fetchedStories = storyIds.mapNotNull { id ->
                    repository.getStoryById(id)
                }
                _stories.value = _stories.value + fetchedStories.sortedByDescending { it.score }
                currentPage++
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    open fun refreshStories() {
        viewModelScope.launch {
            currentPage = 0
            _stories.value = emptyList()
            loadStories()
        }
    }

    open fun loadMoreStories() {
        loadStories()
    }
}

