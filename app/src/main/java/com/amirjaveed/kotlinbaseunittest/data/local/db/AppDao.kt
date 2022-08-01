package com.amirjaveed.kotlinbaseunittest.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.amirjaveed.kotlinbaseunittest.data.models.Spend

@Dao
interface AppDao {

    @Insert
    suspend fun addSpend(spend: Spend)


    @Query("SELECT * FROM spends ORDER BY date DESC LIMIT 20")
    suspend fun getAllSpend(): List<Spend>
}