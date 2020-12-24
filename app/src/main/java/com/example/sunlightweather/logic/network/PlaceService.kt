package com.example.sunlightweather.logic.network

//import android.telecom.Call
import com.example.sunlightweather.SunnyWeatherApplication
import com.example.sunlightweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}