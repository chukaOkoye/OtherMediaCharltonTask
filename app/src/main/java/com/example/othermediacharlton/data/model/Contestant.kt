package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class Contestant(
    @SerializedName("code")
    val code: String,
    @SerializedName("crest")
    val crest: Crest,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortName")
    val shortName: String
)