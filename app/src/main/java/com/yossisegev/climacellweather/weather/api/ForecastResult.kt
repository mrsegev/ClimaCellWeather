package com.yossisegev.climacellweather.weather.api

import com.google.gson.annotations.SerializedName

data class ForecastResult(val temp: ArrayList<Temperature>,
                          @SerializedName("observation_time") val observationTime: ObservationTime
)