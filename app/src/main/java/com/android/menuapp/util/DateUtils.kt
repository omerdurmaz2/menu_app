package com.android.menuapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val apiDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private val dayFormat = SimpleDateFormat("EEEE", Locale.US)
    private val dayNumberFormat = SimpleDateFormat("dd", Locale.US)
    private val monthFormat = SimpleDateFormat("MMMM", Locale.US)

    fun convertApiDateStringToDate(apiDate: String?): Date {
        return apiDateFormat.parse(apiDate)
    }

    fun getDayNumber(date: Long): String {
        return dayNumberFormat.format(date)
    }

    fun getDayName(date: Long): String {
        return dayFormat.format(date)
    }

    fun getMonthName(date: Long): String {
        return monthFormat.format(date)
    }

}