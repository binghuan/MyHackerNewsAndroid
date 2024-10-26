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
import androidx.compose.ui.tooling.preview.Preview
import com.bh.myhackernews_android.data.model.Story
import com.bh.myhackernews_android.presentation.theme.MyHackerNewsAndroidTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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


// Mock ViewModel for Preview (No inheritance from NewsViewModel)
class PreviewNewsViewModel {

    // Dummy Data
    private val _stories = MutableStateFlow(
        listOf(
            Story(
                id = 1,
                by = "User1",
                descendants = 10,
                score = 100,
                time = 1638501294,
                title = "Sample Story 1",
                type = "story",
                url = "https://example.com"
            ), Story(
                id = 2,
                by = "User2",
                descendants = 5,
                score = 80,
                time = 1638501295,
                title = "Sample Story 2",
                type = "story",
                url = "https://example.com"
            )
        )
    )

    // Simulate the ViewModel's StateFlow for the preview
    val stories: StateFlow<List<Story>> = _stories
}


@Preview(showBackground = true)
@Composable
fun PreviewMyHackerNewsApp() {
    // Use PreviewNewsViewModel with dummy data
    val previewViewModel = PreviewNewsViewModel()
    MyHackerNewsAndroidTheme {
        MyHackerNewsApp(viewModel = object : NewsViewModel() {
            override val stories: StateFlow<List<Story>> =
                previewViewModel.stories
            override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        })
    }
}