package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class ProfileData(
    val id : String = "apple@naver.com",
    val profileImage : String = "profileImage",
    val nick : String = "nick",
    val phoneNumber : String = "010-8856-8865",
    val marketingMail : Boolean =  false,
    val marketingSms : Boolean =  false,
    val num : Double = 1.0,
) : Serializable {

}