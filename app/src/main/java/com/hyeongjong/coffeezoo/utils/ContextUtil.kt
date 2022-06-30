package com.hyeongjong.coffeezoo.utils

import android.content.Context
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
        private val SWITCH_SEARCH = "SWITCH_SEARCH"

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
        fun getSearchHistory( context : Context ) : ArrayList<SearchData> {

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

//        delete
        fun deleteSearchHistory( context : Context, searchHistory: ArrayList<SearchData>, position : Int ) : ArrayList<SearchData> {

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            val jSonArray = JSONArray()

            val newSearchHistory = searchHistory

            for ( i in 0 until searchHistory.size ){
                if( i != position ) {
                    val jSonObj = JSONObject()
                    jSonObj.put("search",searchHistory[i].search)
                    jSonObj.put("date",searchHistory[i].date)
                    jSonArray.put(jSonObj)
                }

            }

            newSearchHistory.clear()

            for (i in 0 until jSonArray.length()){
                val jSonObj = jSonArray.getJSONObject(i)
                val search = jSonObj.getString("search")
                val date = jSonObj.getString("date")
                newSearchHistory.add(SearchData(search,date))
            }

            if (!searchHistory.isEmpty()) {
                pref.edit().putString(SEARCH_HISTORY,jSonArray.toString()).apply()
            }
            else{
                pref.edit().putString(SEARCH_HISTORY,null).apply()
            }
            return newSearchHistory
        }

//        all delete
        fun allDeleteSearchHistory( context : Context, searchHistory: ArrayList<SearchData> ) : ArrayList<SearchData> {

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            searchHistory.clear()
            pref.edit().putString(SEARCH_HISTORY,null).apply()

            return searchHistory
        }

        fun setSwitchSearch( context : Context, isSwitchSearch : Boolean ){
//            검색어 스위치가 On인지 Off인지?
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            pref.edit().putBoolean(SWITCH_SEARCH, isSwitchSearch).apply()

        }

        fun getSwitchSearch( context: Context ) : Boolean {

//            메모장을 열고, 저장된 변수를 리턴하자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//            저장된 Switch On/Off 데이터가 없다면 내보내줄 기본값도 설정해야함.
            return pref.getBoolean(SWITCH_SEARCH, false )

        }

    }

}