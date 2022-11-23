package com.example.accommodationreservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var naverMap: NaverMap

    private val mapView: MapView by lazy{ //mapView 변수에 접근했을때 , {} 구문 실행
        
        findViewById(R.id.mapview)

    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //맵뷰를 사용할때 라이프사이클 연결


        mapView.onCreate(savedInstanceState)

        //MapView의 getMapAsync() 메서드로 OnMapReadyCallback을 등록

        mapView.getMapAsync(this)

    }

    //NaverMap 객체가 준비되면 onMapReady() 콜백 메서드가 호출됩니다.

    override fun onMapReady(p0: NaverMap) {
        naverMap = p0
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()

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



}