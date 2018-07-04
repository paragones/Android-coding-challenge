package com.parag.autolabs.ui.main

import android.content.Context
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.parag.autolabs.R
import com.parag.autolabs.models.WeatherResult
import com.parag.autolabs.services.DateUtil
import com.parag.autolabs.services.ImageLoader

class MainAdapter(private val imageLoader: ImageLoader,
                  val weatherResults: List<WeatherResult>,
                  val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val dayFormat = "01d"
    private val nightFormat = "01n"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_list_view, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clRepository = view.findViewById(R.id.clWeatherList) as ConstraintLayout
        val ivWeather = view.findViewById(R.id.ivWeather) as ImageView
        val ivSunrise = view.findViewById(R.id.ivSunrise) as ImageView
        val ivSunset = view.findViewById(R.id.ivSunset) as ImageView
        val tvSunrise = view.findViewById(R.id.tvSunrise) as TextView
        val tvSunset = view.findViewById(R.id.tvSunset) as TextView
        val tvDescription = view.findViewById(R.id.tvDescription) as TextView
        val tvCity = view.findViewById(R.id.tvCity) as TextView
        val tvTemperature = view.findViewById(R.id.tvTemperature) as TextView
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        imageLoader.loadInto(Uri.parse(
                String.format(context.getString(R.string.imageFormat), weatherResults[position].weatherJson[0].icon)),
                holder.ivWeather)
        imageLoader.loadInto(Uri.parse(
                String.format(context.getString(R.string.imageFormat), dayFormat)),
                holder.ivSunrise)
        imageLoader.loadInto(Uri.parse(
                String.format(context.getString(R.string.imageFormat), nightFormat)),
                holder.ivSunset)
        holder.tvSunrise.text = DateUtil.parseDate(weatherResults[position].weatherSystem.sunrise)
        holder.tvSunset.text = DateUtil.parseDate(weatherResults[position].weatherSystem.sunset)
        holder.tvDescription.text = weatherResults[position].weatherJson[0].description
        holder.tvCity.text = weatherResults[position].name
        holder.tvTemperature.text = String.format(context.getString(R.string.temperatureFormat), weatherResults[position].weatherMain.temp)
    }

    override fun getItemCount(): Int = weatherResults.size
}