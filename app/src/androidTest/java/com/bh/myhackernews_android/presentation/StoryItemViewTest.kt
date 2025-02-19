package com.bh.myhackernews_android.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bh.myhackernews_android.data.model.Story
import com.bh.myhackernews_android.presentation.theme.MyHackerNewsAndroidTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StoryItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testStoryItemView() {
        val dummyStory = Story(
            id = 1,
            by = "PreviewUser",
            descendants = 10,
            score = 100,
            time = 1638501294,
            title = "Sample Story",
            type = "story",
            url = "https://example.com"
        )

        composeTestRule.setContent {
            MyHackerNewsAndroidTheme {
                StoryItemView(story = dummyStory)
            }
        }

        composeTestRule.onNodeWithText("Sample Story").assertExists()
        composeTestRule.onNodeWithText("By PreviewUser • Score: 100").assertExists()
    }
}
