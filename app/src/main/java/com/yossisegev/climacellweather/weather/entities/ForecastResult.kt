package com.yossisegev.climacellweather.weather.entities

import com.google.gson.annotations.SerializedName

data class ForecastResult(val temp: ArrayList<WeatherData>,
                          val precipitation: ArrayList<WeatherData>,
                          @SerializedName("observation_time") val observationTime: ObservationTime
)