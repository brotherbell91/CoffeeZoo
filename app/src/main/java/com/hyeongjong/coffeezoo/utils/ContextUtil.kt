package com.hyeongjong.coffeezoo.utils

import android.content.Context
import android.preference.PreferenceManager
import com.hyeongjong.coffeezoo.datas.SearchData
import org.json.JSONArray
import org.json.JSONException


class ContextUtil {

    companion object{

//            일종의 메모장 파일 이름. -> 다른 클래스가 볼 필요x
        private val prefName = "SearchPref"

//            저장할 항목의 이름. (조회할때도 같은 이름 사용)
        private val SEARCH_HISTORY = "SEARCH_HISTORY"

//            해당 항목에 저장 기능 / 조회 기능

//        setter

        fun setSearchHistory( context : Context, searchHistory : ArrayList<String> ){

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            val jSonArray = JSONArray()

            for ( i in 0 until searchHistory.size ){
                jSonArray.put(searchHistory[i])
            }
            if (!searchHistory.isEmpty()) {
                pref.edit().putString(SEARCH_HISTORY,jSonArray.toString()).apply()
            }
            else{
                pref.edit().putString(SEARCH_HISTORY,null).apply()
            }

        }

//        getter
        fun getSearchHistory( context : Context) : ArrayList<String> {

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            val json = pref.getString(SEARCH_HISTORY, null)

            val urls = ArrayList<String>()

            if (json != null) {
                try {
                    val jSonArray = JSONArray(json)
                    for (i in 0 until jSonArray.length()){
                        val url = jSonArray.optString(i)
                        urls.add(url)
                    }

                }catch (e : JSONException) {
                    e.printStackTrace()
                }
            }
            return urls
        }

    }
}