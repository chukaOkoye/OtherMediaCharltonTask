package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("externalId")
    val externalId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("longName")
    val longName: String,
    @SerializedName("shortName")
    val shortName: String
)