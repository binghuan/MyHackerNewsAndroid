package com.bh.myhackernews_android.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bh.myhackernews_android.data.model.Story
import com.bh.myhackernews_android.presentation.theme.MyHackerNewsAndroidTheme

@Composable
fun StoryItemView(story: Story) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth() // Ensure the card fills the full width
            .padding(8.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(story.url))
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Ensure the column fills the full width
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Text(
                text = story.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "By ${story.by} • Score: ${story.score}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
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
        title = "Sample Story",
        type = "story",
        url = "https://example.com"
    )

    MyHackerNewsAndroidTheme {
        StoryItemView(story = dummyStory)
    }
}