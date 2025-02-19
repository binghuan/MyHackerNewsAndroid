package com.bh.myhackernews_android.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bh.myhackernews_android.data.model.Story
import com.bh.myhackernews_android.presentation.theme.MyHackerNewsAndroidTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHackerNewsApp(viewModel: NewsViewModel) {
    // Observe the list of stories and loading state from the ViewModel
    val stories by viewModel.stories.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { index ->
                if (index == stories.size - 1 && !isLoading) {
                    viewModel.loadMoreStories()
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MyHackerNews") },
                actions = {
                    IconButton(onClick = { viewModel.refreshStories() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = Color.White // Change the icon color here
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE), // Use a more prominent color
                    titleContentColor = Color.White // Ensure the title color is readable
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                contentPadding = padding
            ) {
                items(stories) { story ->
                    StoryItemView(story)
                }
                item {
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
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

    fun refreshStories() {
        // No-op for preview
    }

    fun loadMoreStories() {
        // No-op for preview
    }
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
            override fun refreshStories() {
                previewViewModel.refreshStories()
            }

            override fun loadMoreStories() {
                previewViewModel.loadMoreStories()
            }
        })
    }
}