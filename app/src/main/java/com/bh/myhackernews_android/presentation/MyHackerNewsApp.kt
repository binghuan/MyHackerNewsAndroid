package com.bh.myhackernews_android.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHackerNewsApp(viewModel: NewsViewModel) {
    // Observe the list of stories and loading state from the ViewModel
    val stories by viewModel.stories.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("MyHackerNews") })
    }) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                // Show loading indicator in the center of the screen
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                // Show the list of stories
                LazyColumn(contentPadding = padding) {
                    items(stories) { story ->
                        StoryItem(story)
                    }
                }
            }
        }
    }
}
