package com.carsonduckett.weatherviewer

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class Weather (timestamp: Long, minTemp: Double, maxTemp:Double,
               humidity: Double, val description: String, iconName:String) {

    val dayOfWeek = convertTimeStampToDay(timestamp)
    val minTemp:String
    val maxTemp: String
    val humidity: String
    val iconURL: String

    init{
        val numberFormat = NumberFormat.getInstance()
        numberFormat.maximumFractionDigits = 0
        this.minTemp = numberFormat.format(minTemp) +"\u00B0F"
        this.maxTemp = numberFormat.format(maxTemp) + "\u00B0F"
        this.humidity = NumberFormat.getPercentInstance().format(humidity/100.0)
        this.iconURL = "http://openweathermap.org/img/w/$iconName.png"
    }
    private fun convertTimeStampToDay(timeStamp: Long) :String{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp * 1000
        calendar.add(Calendar.MILLISECOND, TimeZone.getDefault().getOffset(calendar.timeInMillis))
        return SimpleDateFormat("EEEE").format(calendar.time)
    }
}