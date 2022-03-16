package com.hyeongjong.coffeezoo.app

import com.hyeongjong.coffeezoo.datas.CafeData
import com.hyeongjong.coffeezoo.datas.SearchData

interface OnCafeClickListener {
    fun onCafeClickEvent(cafe : CafeData)
}