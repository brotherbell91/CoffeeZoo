package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class CafeData(
    val cafeName : String = "CafeName",
    val cafeInsta : String = "CafeInsta",
    val cafeAddress : String = "CafeAddress",
    val latitude : Double = 0.0,
    val longitude : Double= 0.0,
    val cafeNumber : String = "CafeNumber",
    val cafeDescription : String = "CafeDescription",
    val score : Double = 0.0,
    val logoUrl : String = "LogoUrl",
) : Serializable {

}