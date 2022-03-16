package com.hyeongjong.coffeezoo.app

import com.hyeongjong.coffeezoo.datas.CafeData
import com.hyeongjong.coffeezoo.datas.SearchData

interface OnSearchClickListener {
    fun onSearchClickEvent(search : SearchData)
}