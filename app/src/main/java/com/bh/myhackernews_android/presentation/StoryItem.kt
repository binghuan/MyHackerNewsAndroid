package com.bh.myhackernews_android.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bh.myhackernews_android.data.model.Story
import com.bh.myhackernews_android.presentation.theme.MyHackerNewsAndroidTheme

@Composable
fun StoryItem(story: Story) {
    val context =
        LocalContext.current // Access context here in the composable scope

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(story.url))
            context.startActivity(intent) // Use context here to start the activity
        }) {
        Text(text = story.title, style = MaterialTheme.typography.headlineSmall)
        Text(
            text = "By ${story.by} • Score: ${story.score}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStoryItem() {
    val dummyStory = Story(
        id = 1,
        by = "PreviewUser",
        descendants = 10,
        score = 100,
        time = 1638501294,
        title = "Sample Story for Preview",
        type = "story",
        url = "https://example.com"
    )

    MyHackerNewsAndroidTheme {
        StoryItem(story = dummyStory)
    }
}