package com.amirjaveed.kotlinbaseunittest.ui.addspendfragment

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amirjaveed.kotlinbaseunittest.data.local.db.AppDatabase
import com.amirjaveed.kotlinbaseunittest.data.models.Spend
import com.amirjaveed.kotlinbaseunittest.data.remote.reporitory.MainRepository
import com.amirjaveed.kotlinbaseunittest.ui.firstfragment.getOrAwaitValue
import com.google.common.truth.Truth
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddSpendViewModelTest : TestCase(){

    lateinit var mainRepository: MainRepository
    lateinit var addSpendViewModel: AddSpendViewModel
    lateinit var db: AppDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        val context=ApplicationProvider.getApplicationContext<Context>()
         db= Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).allowMainThreadQueries().build()
        val dao=db.appDao()
        mainRepository=MainRepository(null,dao)
        addSpendViewModel=AddSpendViewModel(mainRepository)

    }

    @After
    fun close(){
        db.close()
    }

    @Test
    fun testAddAndGetSpend(){
        val amount=100
        val description="Desc"
        addSpendViewModel.addSpend(100,description)
        addSpendViewModel.getLast20Spends()

       val result= addSpendViewModel.last20SpendsLiveData.getOrAwaitValue().find {
           it.amount == 100 && it.description == "Desc"
        }
        Truth.assertThat(result != null).isTrue()
    }
}