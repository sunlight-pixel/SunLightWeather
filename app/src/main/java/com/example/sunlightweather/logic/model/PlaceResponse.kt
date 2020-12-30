package com.example.sunlightweather.logic.model

//import android.location.Location 该包不得导入，会妨碍我们定义的location
import com.google.gson.annotations.SerializedName

//该文件中定义的类和属性按照搜索城市数据接口返回的json格式定义
//class PlaceResponse(val status: String, val places: List<Place>)

//此处用了@SerializedName注解的方式，让json字段与Kotlin字段之间建立映射关系
//class Place(val name: String, val location: Location,
               //  @SerializedName("formatted_address") val address: String)
//class Location(val lng: String, val lat: String)

class PlaceResponse(val status: String, val places: List<Place>)

class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

class Location(val lng: String, val lat: String)