package com.example.accommodationreservation


import retrofit2.http.GET

//HouseDto(items = [HouseModel(id,title,price),HouseModel(id,title,price)]) 형식으로 저장

data class HouseDto(

    val items : List<HouseModel>

)