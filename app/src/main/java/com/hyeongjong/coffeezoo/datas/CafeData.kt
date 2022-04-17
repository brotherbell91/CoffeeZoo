package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class CafeData(
    val cafeName : String,
    val cafeInsta : String,
    val cafeAddress : String,
    val cafeNumber : String,
    val cafeDescription : String,
    val score : Double,
) : Serializable {



    companion object {
//          카페 데이터
        fun thisMonthList(): ArrayList<CafeData> {
            val cafeList = ArrayList<CafeData>()
            cafeList.clear()
            cafeList.add(CafeData("세가지", "https://www.instagram.com/3egaji/", "경기도 성남시", "0507-1346-1847", "애견동반 가능",4.5))


            return cafeList

        }
    }

}