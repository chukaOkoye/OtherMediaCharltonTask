package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class FixturesDataModel(
    @SerializedName("match")
    val match: ArrayList<Match>
)