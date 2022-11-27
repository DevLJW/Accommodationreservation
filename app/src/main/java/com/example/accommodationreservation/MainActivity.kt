package com.example.accommodationreservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationSource
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var naverMap: NaverMap
    private lateinit var locationSource:FusedLocationSource

    private val mapView: MapView by lazy{ //mapView 변수에 접근했을때 , {} 구문 실행
        
        findViewById(R.id.mapview)

    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //맵뷰를 사용할때 라이프사이클 연결


        mapView.onCreate(savedInstanceState)

        //MapView의 getMapAsync() 메서드로 OnMapReadyCallback을 등록

        mapView.getMapAsync(this) //OnReadyMap 콜백 구현


    }

    //NaverMap 객체가 준비되면 onMapReady() 콜백 메서드가 호출됩니다.

    override fun onMapReady(p0: NaverMap) { //콜백 메소드 구현 으로 NaverMap 객체 얻어 오기

        naverMap = p0 //NaverMap 객체 전역 설정
        naverMap.maxZoom=18.0 //최대 땅길수 있는 확대 18(+)
        naverMap.minZoom=15.0 //최대 축소할 수 있는 줌 15(-)
        //초기 위치를 설정하지 않으면 시청역으로 포커싱되어 있다.
        //위경도로 표시 scrollto(위도경도) latlang(Latlng타입으로 위경도표시)
        val cameraUpdate =  CameraUpdate.scrollTo(LatLng(37.497885,127.027512)) //강남역 위치

        naverMap.moveCamera(cameraUpdate) //초기 카메라 위치값 설정
        val uiSettingbtn = naverMap.uiSettings //지도에서 표시할 수 있는 UI 접근값 가져오기
        uiSettingbtn.isLocationButtonEnabled = true // 내위치를 표기할 수 있는 UI 활성화
        
        //6.0 이상부터는 팝업을 띄워서 권한을 동의할지 아닌지 추가해야댐
        //메인액티비티 this 값과 요청권한 1000
            //최적의 위치를 구현할 수 있는 구현체
        val locationSource = FusedLocationSource(this@MainActivity,LocationPermissionRequest)
    //Google Play 서비스의 FusedLocationProviderClient와 지자기, 가속도 센서를 활용해 최적의 위치를 반환하는 구현체인 FusedLocationSource

        naverMap.locationSource = locationSource //네이버맵에 내위치 허가 권한 주기



    }

    override fun onRequestPermissionsResult( //사용자가 권한을 눌렀을때, 결과
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode != LocationPermissionRequest){ //요청코드가 다를때
            return
        }


//requestCode는 요청할 때 보낸 요청코드이며, grantResults는 요청에 OK를 했을 때의 정보를 갖습니다.
        if(locationSource.onRequestPermissionsResult(requestCode,permissions,grantResults)){
            if(!locationSource.isActivated){ //권한이 거부 되었을때
            naverMap.locationTrackingMode = LocationTrackingMode.None

            }
            return
        }

    }

    override fun onStart() {
        super.onStart()
        mapView.onStart() //맵화면 보여주기

    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }


    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() { //메모리가 얼마 없을때 호출되는 함수
        super.onLowMemory()
        mapView.onLowMemory()
    }

        companion object{

            private const val LocationPermissionRequest = 1000 //권한 요청 값 1000
        }

}