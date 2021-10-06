package com.android.menuapp.model

import com.google.gson.annotations.SerializedName


data class Value(
    @SerializedName("@odata.etag") val oDataETag: String,
    @SerializedName("createdBy") val createdBy: Nothing,
    @SerializedName("createdDateTime") val createdDateTime: String,
    @SerializedName("eTag") val eTag: String,
    @SerializedName("id") val id: Int,
    @SerializedName("lastModifiedBy") val lastModifiedBy: Nothing,
    @SerializedName("lastModifiedDateTime") val lastModifiedDateTime: String,
    @SerializedName("parentReference") val parentReference: Nothing,
    @SerializedName("webUrl") val webUrl: String,
    @SerializedName("contentType") val contentType: Nothing,
    @SerializedName("fields@odata.context") val fieldsODataContext: String,
    @SerializedName("fields") val fields: Fields
)