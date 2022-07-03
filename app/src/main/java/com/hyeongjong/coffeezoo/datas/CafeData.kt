package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class CafeData(
    val cafeName : String,
    val cafeInsta : String,
    val cafeAddress : String,
    val latitude : Double,
    val longitude : Double,
    val cafeNumber : String,
    val cafeDescription : String,
    val score : Double,
    val logoUrl : String,
) : Serializable {



    companion object {
//          카페 데이터
        fun thisMonthList(): ArrayList<CafeData> {
            val cafeList = ArrayList<CafeData>()
            cafeList.clear()
            cafeList.add(CafeData("세가지", "https://www.instagram.com/3egaji/", "경기 성남시 중원구 둔촌대로64번길 4-4 1층", 37.4292571,127.1255657,"0507-1346-1847", "애견동반 가능",4.5, "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20201027_80%2F1603732227717zJhPI_JPEG%2FEnzPvdnD91jXKalHULq58EO6.jpeg.jpg"))
            cafeList.add(CafeData("나이스피플커피", "https://www.instagram.com/nicepeople_coffee/", "경기 성남시 중원구 산성대로 490 2층", 37.4549203,127.1620145,"0507-1313-5631", "애견동반 가능",5.0, "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220404_287%2F1649069322140fcV9K_JPEG%2FF42F64AE-7310-4523-87C0-002C5C9F0E2E.jpeg"))
            cafeList.add(CafeData("어니언 성수", "https://www.instagram.com/cafe.onion/", "서울 성동구 아차산로9길 8 어니언", 37.5446864,127.0582152,"02-1644-1941", "야외 테라스, 베이커리카페",4.0, "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzExMDVfNzIg%2FMDAxNTA5ODc5ODIyNTY2.6y0UNYTB2iJ9QLfkFrw2S7plBdW--ce63QaayzBycnAg.L92i4-RDcs4JqivnQmXS-B8GfskemrT8kWlkLQOHchwg.JPEG.bryanlikes%2Fgh5photo-2017-1104-%25BC%25BA%25BC%25F6_%25BE%25EE%25B4%25CF%25BE%25F0_%25C4%25AB%25C6%25E4-10233.jpg"))
            cafeList.add(CafeData("원아베뉴", "https://www.instagram.com/01avenue_seoul/", "서울 강남구 테헤란로7길 11 1층, B1층", 37.4998502,127.0303763,"0507-1471-0238", "강남역 인근, 디저트카페",4.0, "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20210831_229%2F1630388186520UNvbf_JPEG%2Fimage.jpg"))
            cafeList.add(CafeData("포옥", "https://www.instagram.com/cafe_pooak/", "경기 포천시 소흘읍 죽엽산로 685-20", 37.7748123,127.1658079,"010-2081-5446", "야외 테라스, 서울근교",3.5, "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20210319_170%2F1616132545367DU5sg_JPEG%2FC8H9pHUHRP_E4N4RYClshoXa.jpg"))
            cafeList.add(CafeData("다람이커피", "https://www.instagram.com/darame_coffee/", "경기 용인시 처인구 모현읍 갈월로 172 깊은산속다람쥐2층다람이커피", 37.3247322,127.2620017,"0507-1432-3569", "다람쥐 얼음이 포인트, 야외 테라스, 서울근교",5.0, "https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fmyplace-phinf.pstatic.net%2F20210316_140%2F1615876298570ESzHU_JPEG%2Fupload_7fdaefe2b60a9f2fbc16399664243ac9.jpg"))

            return cafeList

        }
    }

}