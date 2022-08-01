package com.amirjaveed.kotlinbaseunittest.data.remote.reporitory

import com.amirjaveed.kotlinbaseunittest.data.local.db.AppDao
import com.amirjaveed.kotlinbaseunittest.data.remote.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {

    suspend fun getPosts() = apiService.getPosts()

}