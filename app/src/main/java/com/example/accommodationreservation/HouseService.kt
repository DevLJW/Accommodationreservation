package com.example.accommodationreservation

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {

    @GET("//run.mocky.io/v3/34cedef7-9f25-4170-a671-484b71286c22")
    fun getHouseList(): Call<HouseDto>



}