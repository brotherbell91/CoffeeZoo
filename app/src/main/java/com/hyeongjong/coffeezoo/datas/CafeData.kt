package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class CafeData(
    val cafeName : String,
    val cafeInsta : String,
    val cafeAddress : String,
    val latitude : Double,
    val longitude : Double,
    val cafeNumber : String,
    val cafeDescription : String,
    val score : Double,
) : Serializable {



    companion object {
//          카페 데이터
        fun thisMonthList(): ArrayList<CafeData> {
            val cafeList = ArrayList<CafeData>()
            cafeList.clear()
            cafeList.add(CafeData("세가지", "https://www.instagram.com/3egaji/", "경기 성남시 중원구 둔촌대로64번길 4-4 1층", 37.4292571,127.1255657,"0507-1346-1847", "애견동반 가능",4.5))
            cafeList.add(CafeData("나이스피플커피", "https://www.instagram.com/nicepeople_coffee/", "경기 성남시 중원구 산성대로 490 2층", 37.4549203,127.1620145,"0507-1313-5631", "애견동반 가능",5.0))


            return cafeList

        }
    }

}