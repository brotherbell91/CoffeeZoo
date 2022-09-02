package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class ReviewData(
    val cafeName : String = "cafeName",
    val comment : String = "comment",
    val date : String = "2022-01-01 07:07:07",
    val id : String = "apple@naver.com",
    val image : String = "image",
    val nick : String = "nick",
    val score : Double = 1.0,
    val num : Double = 1.0,
) : Serializable {

}