package com.hyeongjong.coffeezoo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hyeongjong.coffeezoo.databinding.ActivityCafeMapBinding
import com.hyeongjong.coffeezoo.datas.CafeData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PathOverlay
import com.naver.maps.map.util.MarkerIcons
import com.odsay.odsayandroidsdk.API
import com.odsay.odsayandroidsdk.ODsayData
import com.odsay.odsayandroidsdk.ODsayService
import com.odsay.odsayandroidsdk.OnResultCallbackListener
import java.text.NumberFormat
import java.util.*

class CafeMapActivity : BaseActivity() {

    lateinit var binding : ActivityCafeMapBinding

    lateinit var mCafeData : CafeData

    //    로딩이 완료된 네이버맵을 담을 변수.
    var mNaverMap : NaverMap? = null // 처음에는 지도도 불러지지 않은 상태.

    //    선택한 출발지 자체를 저장할 변수
    var mSelectedStartPoint : LatLng? = null // 지도에서 클릭한 좌표. 처음에는 아직 없다.

    //    출발지를 띄워줄 마커.
    var mStartMarker: Marker? = null  // 하나의 마커만 만들어서, 출발지를 변경할때마다 위치만 변경되게.

    //    지도에 띄워줄 목적지 표시 마커.
    var mCafeMarker : Marker? = null  // 처음에는 목적지 마커도 없는 상태.

    //    경로선도, 하나만 만들고 계속 재활용.
    var mPath : PathOverlay? = null // 처음에는 경로선도 없는 상태.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cafe_map)

        mCafeData = intent.getSerializableExtra("mapView") as CafeData



        setupEvents()
        setValues()

    }

    override fun setupEvents() {
        //        백버튼 누르면 화면 종료
        binding.imgBack.setOnClickListener {
            finish()
        }

        val toast = Toast.makeText(this,"지도에 출발지를 선택해 주세요", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP,0,300)//메시지 위치 지정 Gravity 상수, x좌표, y좌표
        toast.show()

    }

    override fun setValues() {

        binding.mapView.getMapAsync {

//            불러진 지도를 멤버변수에 저장.
            mNaverMap = it

//          도착지 초기 위치 세팅, 카메라 이동

            val cafeLatLng = LatLng( mCafeData!!.latitude, mCafeData!!.longitude )

            val cameraUpdate =  CameraUpdate.scrollTo( cafeLatLng )
            mNaverMap!!.moveCamera( cameraUpdate )


//        도착지 위치에 마커 찍기
//        아직 마커가 없을때만 생성

            if (mCafeMarker == null) {
                mCafeMarker = Marker()
            }

            mCafeMarker!!.position = cafeLatLng
            mCafeMarker!!.map = mNaverMap


//            네이버 지도의 클릭 이벤트

            mNaverMap!!.setOnMapClickListener { pointF, latLng ->

//                클릭된 좌표는, 출발지로 설정됨.
//                출발지를 멤버변수로 만들어서 => latLng를 출발지로 설정
                mSelectedStartPoint = latLng

//                출발/도착지 그림을 그려주는 함수 재실행.
                setStartAndEndToNaverMap()


            }

//            지도가 불러지고 나서, 출발/도착지 새로 그리기
            setStartAndEndToNaverMap()

        }

    }

    fun setStartAndEndToNaverMap() {

//        혹시 지도가 안불러졌는지? 밑의 코드 실행 X. (안정성 보강)

        if (mNaverMap == null) {
            return
        }

//            mNaverMap은 null 아니다.
        val naverMap = mNaverMap!!

//        출발지가 선택되지 않았는지?
        if (mSelectedStartPoint == null) {
            return
        }

        //  카페 위치가 확인
        if (mCafeData == null) {
            return
        }

//        출발지

        val cameraUpdate =  CameraUpdate.scrollTo( mSelectedStartPoint!! )
        naverMap.moveCamera( cameraUpdate )

//        선택지 마커 없으면 생성

        if (mStartMarker == null) {
            mStartMarker = Marker()
        }

//        위치 이동
        mStartMarker!!.position = mSelectedStartPoint!!
        mStartMarker!!.map = naverMap

//            마커 색상 변경
        mStartMarker!!.icon = MarkerIcons.BLACK // 이 위에 원하는 색 커스텀
        mStartMarker!!.iconTintColor = Color.parseColor("#FF0000") // 안드로이드가 주는 색

//            마커 크기 변경
        mStartMarker!!.width = 50
        mStartMarker!!.height = 80

        //카메라 도착지로 다시 이동

        val cafeLatLng = LatLng( mCafeData!!.latitude, mCafeData!!.longitude )

        val cameraUpdate2 =  CameraUpdate.scrollTo( cafeLatLng!! ).animate(CameraAnimation.Linear) //카메라 이동 에니메이션 추가
        naverMap.moveCamera( cameraUpdate2 )

//        출발지 / 도착지가 모두 반영되는 구조 완성.
//        길찾기 API 호출 => 결과 분석, 화면에 추가 반영. (선 긋기 / 정보 표시)

        val odSay = ODsayService.init(mContext, "RPNRuFYDZSJmndub/Q0Yb16SPfWFgyfezCdK/RrNX1Y")
        odSay.requestSearchPubTransPath(
            mSelectedStartPoint!!.longitude.toString(),
            mSelectedStartPoint!!.latitude.toString(),
            mCafeData!!.longitude.toString(),
            mCafeData!!.latitude.toString(),
            null,
            null,
            null,
            object : OnResultCallbackListener {
                override fun onSuccess(p0: ODsayData?, p1: API?) {

                    val jsonObj = p0!!.json

                    val resultObj = jsonObj.getJSONObject("result")

                    val pathArr = resultObj.getJSONArray("path")

                    val firstPathObj = pathArr.getJSONObject(0)

//                    정보 항목 추출 > InfoWindow 띄우기 (도착지의 마커에 띄우기)

                    val infoObj = firstPathObj.getJSONObject("info")

//                    실제 데이터들은 Obj / Arr 등의 이름을 덧붙이지 않음. (강사 개인 취향)
                    val totalTime = infoObj.getInt("totalTime")
                    val payment = infoObj.getInt("payment")

//                    네이버 지도의 정보창 기능에 연동.

                    val infoWindow = InfoWindow()

                    infoWindow.adapter = object : InfoWindow.DefaultViewAdapter(mContext) {
                        override fun getContentView(p0: InfoWindow): View {

                            val view = LayoutInflater.from(mContext).inflate(R.layout.place_info_window_content, null)

                            val txtPlaceName = view.findViewById<TextView>(R.id.txtPlaceName)
                            val txtTotalTime = view.findViewById<TextView>(R.id.txtTotalTime)
                            val txtPayment = view.findViewById<TextView>(R.id.txtPayment)

                            txtPlaceName.text = mCafeData!!.cafeName

                            txtTotalTime.text = "${totalTime}분 소요"

                            txtPayment.text = "${ NumberFormat.getNumberInstance(Locale.KOREA).format(payment) }원"

                            return view

                        }

                    }

                    infoWindow.open(mCafeMarker!!) // 도착지 마커에 정보창 띄우기


//                    경로선 자체 생성, 첫 좌표는 출발지.

                    if (mPath == null) {
                        mPath = PathOverlay()
                    }

                    val pathCoordList = ArrayList<LatLng>()

                    pathCoordList.add( mSelectedStartPoint!! )


//                    첫번째 경로의 > 세부 경로 파싱 > 경로선 기능으로 그려주기. (정거장 좌표 목록을 경로선 좌표목록에 추가)

                    val subPathArr = firstPathObj.getJSONArray("subPath")

                    for (i  in  0 until subPathArr.length()) {

                        val subPathObj = subPathArr.getJSONObject(i)

                        if (!subPathObj.isNull("passStopList")) {

                            val passStopListObj = subPathObj.getJSONObject("passStopList")

                            val stationsArr = passStopListObj.getJSONArray("stations")

                            for (j in  0 until stationsArr.length()) {

                                val stationObj = stationsArr.getJSONObject(j)

                                val stationLat = stationObj.getString("y").toDouble()
                                val stationLng = stationObj.getString("x").toDouble()

//                                정거장 좌표를 네이버 좌표체계로 만들자. => 경로선의 좌표로 추가.
                                val stationLatLng = LatLng( stationLat, stationLng )

                                pathCoordList.add(stationLatLng)

                            }

                        }

                    }

//                    마지막으로 목적지 좌표 추가.
                    pathCoordList.add( cafeLatLng )


//                    모든 좌표가 추가되었으니, 지도에 나오도록
                    mPath!!.coords = pathCoordList
                    mPath!!.map = naverMap

                }

                override fun onError(p0: Int, p1: String?, p2: API?) {

                }

            }
        )

    }

}