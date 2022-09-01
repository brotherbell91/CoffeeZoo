package com.hyeongjong.coffeezoo.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hyeongjong.coffeezoo.datas.CafeData
import com.hyeongjong.coffeezoo.datas.ReviewData

class ListViewModel : ViewModel() {

    private val repo = Repo()

    fun fetchCafeData() : LiveData<MutableList<CafeData>> {

        val mutableData = MutableLiveData<MutableList<CafeData>>()

        repo.getCafeData().observeForever {

            mutableData.value = it

        }
        return mutableData

    }

    fun fetchReviewData() : LiveData<MutableList<ReviewData>> {

        val mutableData = MutableLiveData<MutableList<ReviewData>>()

        repo.getReviewData().observeForever {

            mutableData.value = it

        }
        return mutableData

    }

    fun fetchCommentData(cafeName : String) : LiveData<MutableList<ReviewData>> {

        val mutableData = MutableLiveData<MutableList<ReviewData>>()

        repo.getCommentData(cafeName).observeForever {

            mutableData.value = it

        }
        return mutableData

    }

}