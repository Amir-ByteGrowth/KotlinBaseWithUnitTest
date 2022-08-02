package com.amirjaveed.kotlinbaseunittest.data.remote.reporitory

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amirjaveed.kotlinbaseunittest.data.local.db.AppDatabase
import com.amirjaveed.kotlinbaseunittest.data.models.PostsResponse
import com.amirjaveed.kotlinbaseunittest.data.remote.ApiService
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response


@RunWith(AndroidJUnit4::class)
class MainRepositoryTest : TestCase() {

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: ApiService

    lateinit var database: AppDatabase

//    @get:Rule
//    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries()
                .build()
        mainRepository = MainRepository(apiService, database.appDao())
    }

    @Test
    fun getAllMovieTest() {
        runBlocking {
            Mockito.`when`(apiService.getPosts()).thenReturn(Response.success(PostsResponse()))
            val response = mainRepository.getPosts()
            assertEquals(PostsResponse(), response!!.body())
        }

    }

}