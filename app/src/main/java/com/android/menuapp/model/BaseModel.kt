package com.android.menuapp.model

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("odata.context") val dataContext: String,
    @SerializedName("value") val value: List<Value>
)