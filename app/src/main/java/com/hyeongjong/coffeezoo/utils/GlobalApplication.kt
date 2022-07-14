package com.hyeongjong.coffeezoo.utils

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "03c1e24289cd88918bd9cbed869745aa")
    }

}