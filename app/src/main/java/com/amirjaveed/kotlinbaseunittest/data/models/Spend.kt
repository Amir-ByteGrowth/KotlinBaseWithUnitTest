package com.amirjaveed.kotlinbaseunittest.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity(tableName = "spends")
data class Spend(val amount: Int, val description: String, val date: Date){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}