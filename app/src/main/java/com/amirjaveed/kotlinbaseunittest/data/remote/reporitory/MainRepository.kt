package com.amirjaveed.kotlinbaseunittest.data.remote.reporitory

import com.amirjaveed.kotlinbaseunittest.data.local.db.AppDao
import com.amirjaveed.kotlinbaseunittest.data.models.Spend
import com.amirjaveed.kotlinbaseunittest.data.remote.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService?,
    private val localDataSource: AppDao
) {

    suspend fun getPosts() = apiService?.getPosts()

    suspend fun addSpend(spend: Spend)=localDataSource.addSpend(spend)

    suspend fun getAllSpend()=localDataSource.getAllSpend()

}