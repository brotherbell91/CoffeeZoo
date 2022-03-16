package com.hyeongjong.coffeezoo.datas

import android.media.Image
import java.io.Serializable

class SearchData(
    val search : String,
    val date : String,
) : Serializable {



    companion object {
        //          검색 데이터
        fun latelySearchList(): ArrayList<SearchData> {
            val searchList = ArrayList<SearchData>()
            searchList.clear()
            searchList.add(SearchData("세가지","2022.04.12"))
            searchList.add(SearchData("다람쥐","2022.04.12"))
            searchList.add(SearchData("투썸","2022.04.12"))


            return searchList

        }
    }

}