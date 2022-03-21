package com.hyeongjong.coffeezoo.utils

import android.content.Context
import android.preference.PreferenceManager
import android.provider.ContactsContract
import android.service.autofill.UserData
import com.hyeongjong.coffeezoo.datas.SearchData
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class ContextUtil {

    companion object{

//            일종의 메모장 파일 이름. -> 다른 클래스가 볼 필요x
        private val prefName = "SearchPref"

//            저장할 항목의 이름. (조회할때도 같은 이름 사용)
        private val SEARCH_HISTORY = "SEARCH_HISTORY"

//            해당 항목에 저장 기능 / 조회 기능

//        setter

        fun setSearchHistory( context : Context, searchHistory : ArrayList<SearchData> ){

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            val jSonArray = JSONArray()

            for ( i in 0 until searchHistory.size ){
                val jSonObj = JSONObject()
                jSonObj.put("search",searchHistory[i].search)
                jSonObj.put("date",searchHistory[i].date)
                jSonArray.put(jSonObj)
            }
            if (!searchHistory.isEmpty()) {
                pref.edit().putString(SEARCH_HISTORY,jSonArray.toString()).apply()
            }
            else{
                pref.edit().putString(SEARCH_HISTORY,null).apply()
            }

        }

//        getter
        fun getSearchHistory( context : Context) : ArrayList<SearchData> {

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            val json = pref.getString(SEARCH_HISTORY, null)

            val searchHistory = ArrayList<SearchData>()

            if (json != null) {
                try {
                    val jSonArray = JSONArray(json)
                    for (i in 0 until jSonArray.length()){
                        val jSonObj = jSonArray.getJSONObject(i)
                        val search = jSonObj.getString("search")
                        val date = jSonObj.getString("date")
                        searchHistory.add(SearchData(search,date))
                    }

                }catch (e : JSONException) {
                    e.printStackTrace()
                }
            }
            return searchHistory
        }

    }

}