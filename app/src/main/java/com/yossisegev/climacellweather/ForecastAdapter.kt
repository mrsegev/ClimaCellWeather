package com.yossisegev.climacellweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kinecosystem.myapplication.R
import com.yossisegev.climacellweather.weather.entities.SimpleWeather
import kotlinx.android.synthetic.main.forecast_row.view.*


class ForecastAdapter(private val temperatures: List<SimpleWeather>) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_row, parent, false)
        return ForecastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return temperatures.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(temperatures[position])
    }


    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(temperature: SimpleWeather) = with (itemView) {
            forecast_row_max.text = temperature.maxTemp.toString()
            forecast_row_min.text = temperature.minTemp.toString()
            forecast_row_date.text = temperature.time
        }
    }
}

