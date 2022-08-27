package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class ProfileData(
    var id : String = "apple@naver.com",
    var profileImage : String = "profileImage",
    var nick : String = "nick",
    var phoneNumber : String = "010-0000-0000",
    var marketingMail : Boolean =  false,
    var marketingSms : Boolean =  false,
    var num : Double = 1.0,
) : Serializable {

}