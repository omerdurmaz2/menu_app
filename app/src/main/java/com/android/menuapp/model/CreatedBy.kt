package com.android.menuapp.model

import com.google.gson.annotations.SerializedName

data class CreatedBy (
	@SerializedName("user") val user : User
)