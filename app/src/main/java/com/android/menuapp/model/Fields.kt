package com.android.menuapp.model

import com.google.gson.annotations.SerializedName

data class Fields (

	@SerializedName("@odata.etag") val oDataETag : String,
	@SerializedName("Title") val title : String,
	@SerializedName("FoodCategory") val foodCategory : String,
	@SerializedName("Calorie") val calorie : Int,
	@SerializedName("ItemStartDate") val itemStartDate : String,
	@SerializedName("id") val id : Int
)