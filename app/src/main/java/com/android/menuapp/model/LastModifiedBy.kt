package com.android.menuapp.model

import com.google.gson.annotations.SerializedName

data class LastModifiedBy (
	@SerializedName("user") val user : User
)