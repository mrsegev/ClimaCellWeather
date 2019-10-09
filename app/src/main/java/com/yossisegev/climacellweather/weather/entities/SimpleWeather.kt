package com.yossisegev.climacellweather.weather.entities

class SimpleWeather (forecastResult: ForecastResult) {

    var minTemp: Double = 0.0
    var maxTemp: Double = 0.0
    var minPrecipitation: Double = 0.0
    var maxPrecipitation: Double = 0.0
    var time: String = ""

    init {
        time = forecastResult.observationTime.value

        forecastResult.temp.forEach { temp ->
            if (temp.max != null) {
                this.maxTemp = temp.max.value
            } else if (temp.min != null) {
                this.minTemp = temp.min.value
            }
        }

        forecastResult.precipitation.forEach { percipitation ->
            if (percipitation.max != null) {
                this.maxPrecipitation = percipitation.max.value
            } else if (percipitation.min != null) {
                this.minPrecipitation = percipitation.min.value
            }
        }
    }
}