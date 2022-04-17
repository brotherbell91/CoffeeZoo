package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class CafeData(
    val cafeName : String,
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
            cafeList.add(CafeData("세가지", "경기도 성남시", "02-1234-5678", "애견동반카페", 5.0))
            cafeList.add(CafeData("네가지", "경기도 성남시", "02-5555-6666", "애견동반카페", 5.0))
            cafeList.add(CafeData("다섯가지", "경기도 성남시", "02-6666-7777", "애견동반카페", 5.0))
            cafeList.add(CafeData("일곱가지", "경기도 성남시", "02-8888-9999", "애견동반카페", 5.0))
            cafeList.add(CafeData("열가지", "경기도 성남시", "02-9999-0000", "애견동반카페", 5.0))
            cafeList.add(CafeData("네가지", "경기도 성남시", "032-7777-8888", "애견동반카페", 5.0))
            cafeList.add(CafeData("한가지", "경기도 성남시", "02-4567-7415", "쿠키맛집", 4.5))
            cafeList.add(CafeData("나뭇가지", "경기도 성남시", "02-4561-6587", "다람쥐동반", 5.0))
            cafeList.add(CafeData("세가지", "경기도 성남시", "02-4512-4587", "애견동반카페", 5.0))
            cafeList.add(CafeData("세가지", "경기도 성남시", "02-1234-5678", "애견동반카페", 5.0))
            cafeList.add(CafeData("세가지", "경기도 성남시", "02-1234-5678", "애견동반카페", 5.0))

            return cafeList

        }
    }

}