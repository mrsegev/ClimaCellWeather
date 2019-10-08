package com.yossisegev.climacellweather.weather.api


import com.yossisegev.climacellweather.weather.api.ForecastResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather/forecast/daily?fields=temp")
    fun getDailyForecast(@Query("lat") lat: Double, @Query("lon") lon: Double): Observable<ArrayList<ForecastResult>>


    fun getCurrent(locationId: String): Observable<ArrayList<ForecastResult>>
}