package com.amirjaveed.kotlinbaseunittest.data.remote

import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUsers() = apiService.getPosts()
}