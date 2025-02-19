package com.bh.myhackernews_android.presentation

import com.bh.myhackernews_android.data.api.HackerNewsService
import com.bh.myhackernews_android.data.model.Story
import com.bh.myhackernews_android.data.repository.StoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {

    @Mock
    private lateinit var hackerNewsService: HackerNewsService

    private lateinit var repository: StoryRepository
    private lateinit var viewModel: NewsViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        repository = StoryRepository(hackerNewsService)
        viewModel = NewsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLoadStories() = runTest {
        val dummyStories = listOf(
            Story(1, "User1", 10, 100, 1638501294, "Sample Story 1", "story", "https://example.com"),
            Story(2, "User2", 5, 80, 1638501295, "Sample Story 2", "story", "https://example.com")
        )
        Mockito.`when`(hackerNewsService.getNewStories()).thenReturn(listOf(1L, 2L))
        Mockito.`when`(hackerNewsService.getStoryById(1L)).thenReturn(dummyStories[0])
        Mockito.`when`(hackerNewsService.getStoryById(2L)).thenReturn(dummyStories[1])

        viewModel.refreshStories()

        val stories = viewModel.stories.first()
        assertEquals(dummyStories, stories)
    }

    @Test
    fun testRefreshStories() = runTest {
        val dummyStories = listOf(
            Story(1, "User1", 10, 100, 1638501294, "Sample Story 1", "story", "https://example.com"),
            Story(2, "User2", 5, 80, 1638501295, "Sample Story 2", "story", "https://example.com")
        )
        Mockito.`when`(hackerNewsService.getNewStories()).thenReturn(listOf(1L, 2L))
        Mockito.`when`(hackerNewsService.getStoryById(1L)).thenReturn(dummyStories[0])
        Mockito.`when`(hackerNewsService.getStoryById(2L)).thenReturn(dummyStories[1])

        viewModel.refreshStories()

        val stories = viewModel.stories.first()
        assertEquals(dummyStories, stories)
    }

    @Test
    fun testLoadMoreStories() = runTest {
        val initialStories = listOf(
            Story(1, "User1", 10, 100, 1638501294, "Sample Story 1", "story", "https://example.com")
        )
        val moreStories = listOf(
            Story(2, "User2", 5, 80, 1638501295, "Sample Story 2", "story", "https://example.com")
        )
        Mockito.`when`(hackerNewsService.getNewStories()).thenReturn(listOf(1L, 2L))
        Mockito.`when`(hackerNewsService.getStoryById(1L)).thenReturn(initialStories[0])
        Mockito.`when`(hackerNewsService.getStoryById(2L)).thenReturn(moreStories[0])

        viewModel.refreshStories()
        viewModel.loadMoreStories()

        val stories = viewModel.stories.first()
        assertEquals(initialStories + moreStories, stories)
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
