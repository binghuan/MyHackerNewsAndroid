package com.bh.myhackernews_android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bh.myhackernews_android.data.api.HackerNewsApiClient
import com.bh.myhackernews_android.data.repository.StoryRepository
import com.bh.myhackernews_android.presentation.theme.MyHackerNewsAndroidTheme

class MainActivity : ComponentActivity() {

    private val viewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(StoryRepository(HackerNewsApiClient.hackerNewsService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyHackerNewsAndroidTheme {
                MyHackerNewsApp(viewModel = viewModel)
            }
        }
    }
}

class NewsViewModelFactory(private val repository: StoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
