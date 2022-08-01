package com.amirjaveed.kotlinbaseunittest.ui.firstfragment

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirjaveed.kotlinbaseunittest.data.models.PostsResponse
import com.amirjaveed.kotlinbaseunittest.data.models.Spend
import com.amirjaveed.kotlinbaseunittest.data.remote.Resource
import com.amirjaveed.kotlinbaseunittest.data.remote.reporitory.MainRepository
import com.amirjaveed.kotlinbaseunittest.utils.NetworkHelper
import kotlinx.coroutines.launch
import java.util.*


class FirstViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {


    private val _posts = MutableLiveData<Resource<PostsResponse>>()
    val postsData: LiveData<Resource<PostsResponse>>
        get() = _posts


    fun fetchPostsFromApi() {
        viewModelScope.launch {
            _posts.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getPosts().let {
                    if (it!!.isSuccessful) {
                        _posts.postValue(Resource.success(it.body()!!))
                    } else _posts.postValue(Resource.error(it.message(), null))
                }
            } else _posts.postValue(Resource.error("No internet connection", null))
        }
    }


}