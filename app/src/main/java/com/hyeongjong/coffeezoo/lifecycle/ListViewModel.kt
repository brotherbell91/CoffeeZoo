package com.hyeongjong.coffeezoo.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hyeongjong.coffeezoo.datas.CafeData

class ListViewModel : ViewModel() {

    private val repo = Repo()

    fun fetchData() : LiveData<MutableList<CafeData>> {

        val mutableData = MutableLiveData<MutableList<CafeData>>()

        repo.getData().observeForever {

            mutableData.value = it

        }
        return mutableData

    }
}