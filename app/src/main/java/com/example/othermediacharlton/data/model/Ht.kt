package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class Ht(
    @SerializedName("away_score")
    val awayScore: Int,
    @SerializedName("home_score")
    val homeScore: Int
)