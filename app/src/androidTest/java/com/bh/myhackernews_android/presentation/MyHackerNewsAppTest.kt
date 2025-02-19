package com.bh.myhackernews_android.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bh.myhackernews_android.data.api.HackerNewsService
import com.bh.myhackernews_android.data.model.Story
import com.bh.myhackernews_android.data.repository.StoryRepository
import com.bh.myhackernews_android.presentation.theme.MyHackerNewsAndroidTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyHackerNewsAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMyHackerNewsApp() {
        val dummyStories = listOf(
            Story(
                id = 1,
                by = "User1",
                descendants = 10,
                score = 100,
                time = 1638501294,
                title = "Sample Story 1",
                type = "story",
                url = "https://example.com"
            ),
            Story(
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

        val previewViewModel = object : NewsViewModel(StoryRepository(MockHackerNewsService())) {
            override val stories: StateFlow<List<Story>> = MutableStateFlow(dummyStories)
            override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
            override fun refreshStories() {}
            override fun loadMoreStories() {}
        }

        composeTestRule.setContent {
            MyHackerNewsAndroidTheme {
                MyHackerNewsApp(viewModel = previewViewModel)
            }
        }

        composeTestRule.onNodeWithText("Sample Story 1").assertExists()
        composeTestRule.onNodeWithText("Sample Story 2").assertExists()
    }
}

// Mock HackerNewsService for testing
class MockHackerNewsService : HackerNewsService {
    override suspend fun getNewStories(): List<Long> {
        return listOf(1L, 2L)
    }

    override suspend fun getStoryById(id: Long): Story {
        return Story(
            id = id,
            by = "User$id",
            descendants = 10,
            score = 100,
            time = 1638501294,
            title = "Sample Story $id",
            type = "story",
            url = "https://example.com"
        )
    }
}
