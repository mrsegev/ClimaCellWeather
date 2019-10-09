package com.yossisegev.climacellweather.weather.entities

class SimpleWeather (forecastResult: ForecastResult) {

    var minTemp: String = ""
    var maxTemp: String = ""
    var minPrecipitation: String = ""
    var maxPrecipitation: String = ""
    var date: String = ""

    init {
        date = forecastResult.observationTime.value

        forecastResult.temp.forEach { temp ->
            if (temp.max != null) {
                this.maxTemp = "Max: ${temp.max.value}"
            } else if (temp.min != null) {
                this.minTemp = "Min: ${temp.min.value}"
            }
        }

        forecastResult.precipitation.forEach { precipitation ->
            if (precipitation.max != null) {
                this.maxPrecipitation = "Precipitation: ${precipitation.max.value}"
            } else if (precipitation.min != null) {
                this.minPrecipitation = "Precipitation: ${precipitation.min.value}"
            }
        }
    }
}