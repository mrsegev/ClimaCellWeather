package com.yossisegev.climacellweather.weather

import com.yossisegev.climacellweather.weather.api.ObservationTime
import com.yossisegev.climacellweather.weather.api.Temperature

class SimpleTemp (temperatures: List<Temperature>, observationTime: ObservationTime) {

    var min: Double = 0.0
    var max: Double = 0.0
    var time: String = ""

    init {
        time = observationTime.value

        temperatures.forEach { temp ->
            if (temp.max != null) {
                this.max = temp.max.value
            } else if (temp.min != null) {
                this.min = temp.min.value
            }
        }
    }
}