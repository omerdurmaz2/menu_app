package com.android.menuapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val locale = Locale("tr", "TR")
    private val apiDateFormat = SimpleDateFormat("yyyy-MM-dd", locale)
    private val dayFormat = SimpleDateFormat("EEEE", locale)
    private val dayNumberFormat = SimpleDateFormat("dd", locale)
    private val monthFormat = SimpleDateFormat("MMMM", locale)

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