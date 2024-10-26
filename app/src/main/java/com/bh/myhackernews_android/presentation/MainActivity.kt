package com.bh.myhackernews_android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.bh.myhackernews_android.presentation.theme.MyHackerNewsAndroidTheme

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
