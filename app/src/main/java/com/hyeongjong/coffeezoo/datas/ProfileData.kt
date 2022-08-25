package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class ProfileData(
    val profileImage : String = "profileImage",
    val nick : String = "nick",
    val phoneNumber : String = "010-0000-0000",
    val marketingMail : Boolean =  false,
    val marketingSms : Boolean =  false,
) : Serializable {

}