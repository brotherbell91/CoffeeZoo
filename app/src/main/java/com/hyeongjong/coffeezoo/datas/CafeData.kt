package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class CafeData(
    val cafeName : String,
    val cafeAddress : String,
    val cafeDescription : String,
    val score : Double,
) : Serializable {



    companion object {
//          카페 데이터
        fun thisMonthList(): ArrayList<CafeData> {
            val cafeList = ArrayList<CafeData>()
            cafeList.clear()
            cafeList.add(CafeData("세가지", "경기도 성남시", "애견동반가능", 5.0))
            cafeList.add(CafeData("깊은산속다람쥐", "경기도 포천시", "다람쥐보임", 4.5))
            cafeList.add(CafeData("카페gg", "경기도성남시", "디저트카페", 5.0))
            cafeList.add(CafeData("생각의탄생", "경기도 성남시", "빵이맛있는집",3.5))
            cafeList.add(CafeData("서현170", "경기도 성남시", "스터디카페", 4.0))
            cafeList.add(CafeData("새소리물소리", "경기도 성남시", "도넛맛집", 5.0))
            cafeList.add(CafeData("카페랄로", "경기도 성남시", "디저트맛집", 3.5))
            cafeList.add(CafeData("파네트리", "경기도 성남시", "애견동반가능", 5.0))
            cafeList.add(CafeData("핑거크로스", "경기도 성남시", "블러커피바", 4.5))
            cafeList.add(CafeData("모아니", "경기도 성남시", "넓은부지", 3.5))
            cafeList.add(CafeData("키로베이커리", "경기도 성남시", "호랭이롤", 4.5))

            return cafeList

        }
    }

}