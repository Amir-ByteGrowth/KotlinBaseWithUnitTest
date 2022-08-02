package com.amirjaveed.kotlinbaseunittest.ui.addspendfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirjaveed.kotlinbaseunittest.data.models.Spend
import com.amirjaveed.kotlinbaseunittest.data.remote.repository.MainRepository
import kotlinx.coroutines.launch
import java.util.*

class AddSpendViewModel(private val mainRepository: MainRepository):ViewModel() {
    private val _last20SpendsLiveData = MutableLiveData<List<Spend>>()
    val last20SpendsLiveData: LiveData<List<Spend>>
        get() = _last20SpendsLiveData

    fun addSpend(amount: Int, description: String) = viewModelScope.launch {
        mainRepository.addSpend(Spend(amount,description,Date()))
    }

    fun getLast20Spends() = viewModelScope.launch {
        _last20SpendsLiveData.value = mainRepository.getAllSpend()
        Log.d("ValuesFound",last20SpendsLiveData.value.toString())
    }
}