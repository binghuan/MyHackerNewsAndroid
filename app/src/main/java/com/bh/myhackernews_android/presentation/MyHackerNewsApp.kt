package com.bh.myhackernews_android.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHackerNewsApp(viewModel: NewsViewModel) {
    // Observe the list of stories from the ViewModel
    val stories by viewModel.stories.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("MyHackerNews") })
    }) { padding ->
        LazyColumn(contentPadding = padding) {
            items(stories) { story ->
                StoryItem(story)
            }
        }
    }
}

