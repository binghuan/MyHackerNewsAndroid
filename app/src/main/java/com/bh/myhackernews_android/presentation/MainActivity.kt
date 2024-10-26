package com.bh.myhackernews_android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bh.myhackernews_android.data.model.Story
import com.bh.myhackernews_android.presentation.theme.MyHackerNewsAndroidTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyHackerNewsAndroidTheme {
                MyHackerNewsApp(viewModel = viewModel)
            }
        }
    }
}


// Mock ViewModel for Preview
class PreviewNewsViewModel : NewsViewModel() {

    // Dummy Data
    fun getDummyStories(): List<Story> {
        return listOf(
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
    }

    private val _stories = MutableStateFlow(getDummyStories())
    override val stories: StateFlow<List<Story>> = _stories
}

@Preview(showBackground = true)
@Composable
fun PreviewMyHackerNewsApp() {
    val previewViewModel = PreviewNewsViewModel()
    MyHackerNewsAndroidTheme {
        MyHackerNewsApp(viewModel = previewViewModel)
    }
}