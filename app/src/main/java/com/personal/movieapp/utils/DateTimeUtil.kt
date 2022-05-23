package com.personal.movieapp.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Rishabh
 */
object DateTimeUtil {

    const val date_MMM_DD_YYYY: String = "MMMM dd, yyyy"
    const val date_YYYY_MM_DD: String = "yyyy-MM-dd"

    fun genericDateFormatter(
        dateTime: String?,
        currentTimeFormat: String?,
        requiredTimeFormat: String?
    ): String? {
        var time = ""
        val date: Date?
        try {
            dateTime?.let {
                date = if (!currentTimeFormat.isNullOrEmpty()) {
                    val simpleDateFormat = SimpleDateFormat(currentTimeFormat, Locale.ENGLISH)
                    simpleDateFormat.parse(it)
                } else {
                    Date(it.toLong())
                }
                val simpleDateFormat = SimpleDateFormat(requiredTimeFormat, Locale.ENGLISH)
                date?.apply { time = simpleDateFormat.format(this) }
            }
        } catch (e: Exception) {

        }
        return time
    }
}