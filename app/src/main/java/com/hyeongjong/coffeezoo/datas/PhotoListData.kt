package com.hyeongjong.coffeezoo.datas

import java.io.Serializable

class PhotoListData(
    val image : String,
    val comment : String,
) : Serializable {

    companion object {
//          사진 데이터
        fun photoList() : ArrayList<PhotoListData> {
            val popularPhotoList = ArrayList<PhotoListData>()
            popularPhotoList.clear()
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220409_80%2F1649486372942p7Dbm_JPEG%2FKakaoTalk_20220409_153051519.jpg", "너무 귀여운 흑임자색 갱얼쥐가 있는곳\uD83D\uDC3E"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220413_244%2F16498115583776Owow_JPEG%2F3%25C3%25FE%25C5%25D7%25B6%25F3%25BD%25BA.jpg", "도넛이 너무 맛있었던 곳 :)"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220701_169%2F1656650736282wpQan_JPEG%2F7.jpg", "푸딩이 정말 맛있었던 그곳"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20210718_96%2F1626614369967tDTBy_JPEG%2FAXKQj5IxiiKqABXo-Vgtelz8.jpg", "바다뷰가 너무 아름다웠어요 항상 생각나네요"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220104_32%2F16412921173623ufPm_JPEG%2F1641291497832-10.jpg", "바닷가에 위치해 있어서 바다를 보며 분위기를 마셨어요"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200724_290%2F1595580271798OyLSf_JPEG%2Fikr4wZpH4bL3QzUWab_3vVDl.jpg", "식빵이 사르르 녹는게 너무 맛있네요"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20210716_15%2F1626436684570STPz3_JPEG%2Fp-Z3iaTMpwOMNwvt6kVmyPHq.jpg", "항상 가고싶다가 제주도 간김에 바로 들렸네요"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220517_224%2F1652799458238AeU3V_JPEG%2F%25C8%25DE%25C0%25CF%25B7%25CE_%252812%2529.jpg","야자수가 너무 예쁘고 바다가 반짝이네요"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200505_159%2F1588683639393gzzC2_JPEG%2F5m3JOitceORBgalH3J9gcGju.jpeg.jpg", "마음이 차분해지던 곳"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTAzMjRfMTkw%2FMDAxNjE2NTYwNDc4NzQ2.glXhUL3NCXaLgegeZqrCUJ-9gBtWfg7XIRkeUwj9ZV4g.SUtNbCu6hOPouYwvHQcBstTTLSRTKMOlqbEE0RMzrPkg.JPEG.lila8333%2F09E079FB-CA5D-4340-BD78-BEDA98FE93E1.jpg", "야자수와 도넛이 잘 어우러진 카페"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTEwMTNfMTU2%2FMDAxNjM0MTA0NTI2NTk2.lcalnCPrw51oa0knW4b6kL4l1j_a7UmHjsNsrW6_0zYg.wNwpv0jCC16yI4wxD-kdZsjM8ojudwnDkDBYg-Ve3q0g.JPEG.tjsghk1753%2FIMG_2646.JPG", "핑크색 곰돌이 너무 귀엽지 않나요?"))
            popularPhotoList.add(PhotoListData("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=https%3A%2F%2Fpup-review-phinf.pstatic.net%2FMjAyMjA3MDNfMTMy%2FMDAxNjU2ODE4MDEyOTMw.R9UljQEihHNnzLCkHsV5HY43PyoL_LAdrJoK4gbfWTAg.eJNV9m_vD8kAl1n5_IaoDutGFOGnWOSzFMnkZb9JLcMg.JPEG%2Fupload_a03daf42bc555db35fbe34f929a2e1f8.jpg", "칵테일과 커피가 공존하던 그곳"))

    return popularPhotoList

        }
    }
}