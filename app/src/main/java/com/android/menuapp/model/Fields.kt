package com.android.menuapp.model

import com.android.menuapp.util.DateUtils
import com.google.gson.annotations.SerializedName
import java.util.*

data class Fields(

    @SerializedName("@odata.etag") val oDataETag: String,
    @SerializedName("Title") val title: String,
    @SerializedName("FoodCategory") val foodCategory: String,
    @SerializedName("Calorie") val calorie: Int,
    @SerializedName("ItemStartDate") val itemStartDate: String,
    @SerializedName("id") val id: Int
) {
    fun getDay(): Date {
        return DateUtils.convertApiDateStringToDate(itemStartDate)
    }
}