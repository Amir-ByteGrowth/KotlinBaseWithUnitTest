package com.amirjaveed.kotlinbaseunittest.data.local.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amirjaveed.kotlinbaseunittest.data.models.Spend
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {
    lateinit var db: AppDatabase
    lateinit var dao: AppDao

    @Before
  public  override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

        dao=db.appDao()
    }

    @After
    fun close(){
        db.close()
    }

     @Test
     fun validateIfDataIsInsertedAndFetched()= runBlocking{
         val spend=Spend(100,"description", Date())
         dao.addSpend(spend)
         val spends=dao.getAllSpend()
         assertThat(spends.contains(spend)).isTrue()
     }

}