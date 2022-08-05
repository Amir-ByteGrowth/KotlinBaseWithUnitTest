package com.amirjaveed.kotlinbaseunittest.ui.firstfragment

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amirjaveed.kotlinbaseunittest.data.local.db.AppDatabase
import com.amirjaveed.kotlinbaseunittest.data.models.PostsResponse
import com.amirjaveed.kotlinbaseunittest.data.models.PostsResponseItem
import com.amirjaveed.kotlinbaseunittest.data.remote.ApiService
import com.amirjaveed.kotlinbaseunittest.data.remote.Resource
import com.amirjaveed.kotlinbaseunittest.data.remote.repository.MainRepository
import com.amirjaveed.kotlinbaseunittest.utils.NetworkHelper
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FirstViewModelTest : TestCase() {


    lateinit var netWorkHelper: NetworkHelper
    lateinit var database: AppDatabase
    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: FirstViewModel
    lateinit var mainRepository: MainRepository


    @Mock
    lateinit var apiService: ApiService

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context=ApplicationProvider.getApplicationContext<Context>()
        netWorkHelper=NetworkHelper(context)
        database=Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).allowMainThreadQueries().build()

        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = MainRepository(apiService,database.appDao())
        mainViewModel = FirstViewModel(mainRepository,netWorkHelper)
    }


    @Test
    fun getAllMoviesTest() {

        val postsResponse=PostsResponse()
        postsResponse.add(PostsResponseItem("abc",1,"title",1))
        val postsResponse2=PostsResponse()
        runBlocking {
            Mockito.`when`(mainRepository.getPosts())
                .thenReturn(Response.success(postsResponse))
            mainViewModel.fetchPostsFromApi()
            val result = mainViewModel.postsData.getOrAwaitValue()
            assertEquals(Resource.success(postsResponse2), result)
        }
    }


    @Test
    fun empty_movie_list_test() {
        val postsResponse=PostsResponse()

        runBlocking {
            Mockito.`when`(mainRepository.getPosts())
                .thenReturn(Response.success(postsResponse))
            mainViewModel.fetchPostsFromApi()
            val result = mainViewModel.postsData.getOrAwaitValue()
            assertEquals(Resource.success(postsResponse), result)
        }
    }

}