package com.android.menuapp.model

import com.google.gson.annotations.SerializedName

data class User (

	@SerializedName("id") val id : String,
	@SerializedName("displayName") val displayName : String
)