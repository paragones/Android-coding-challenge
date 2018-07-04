package com.parag.autolabs.services

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    const val HH_MM = "hh:mm a"

    fun parseDate(dateLong: Long): String {
        val simpleDateFormat = SimpleDateFormat(HH_MM, Locale.getDefault())
        return simpleDateFormat.format(Date(dateLong))
    }

}