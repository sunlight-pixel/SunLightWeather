package com.example.sunlightweather.ui.weather

//import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.sunlightweather.logic.Repository
import com.example.sunlightweather.logic.model.Location //导包时要注意导的包是什么

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    var weatherLiveData = Transformations.switchMap(locationLiveData) {
        location -> Repository.refreshWeather(location.lng, location.lat,placeName)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

}