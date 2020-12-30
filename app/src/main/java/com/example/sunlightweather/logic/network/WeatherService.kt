package com.example.sunlightweather.logic.network

import com.example.sunlightweather.SunnyWeatherApplication
import com.example.sunlightweather.logic.model.DailyResponse
import com.example.sunlightweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
//@GET注解来声明要访问的API接口
//@Path注解来向请求接口中动态传入经纬度的坐标
interface WeatherService {

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    //获取实时的天气信息
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<RealtimeResponse>
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    //获取未来的天气信息
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<DailyResponse>
}