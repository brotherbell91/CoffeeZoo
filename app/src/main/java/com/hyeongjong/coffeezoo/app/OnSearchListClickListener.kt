package com.hyeongjong.coffeezoo.app

import com.hyeongjong.coffeezoo.datas.CafeData
import com.hyeongjong.coffeezoo.datas.SearchData

interface OnSearchListClickListener {
    fun onSearchListClickEvent(search : SearchData)
}