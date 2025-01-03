package com.iskan.dicodingevent.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {
    fun eventDate(beginTime: String, endTime: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val outputTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        val beginDate = inputFormat.parse(beginTime)
        val endDate = inputFormat.parse(endTime)

        val formattedDate = outputDateFormat.format(beginDate!!)
        val beginFormattedTime = outputTimeFormat.format(beginDate)
        val endFormattedTime = outputTimeFormat.format(endDate!!)

        return "$formattedDate $beginFormattedTime - $endFormattedTime"
    }
}
