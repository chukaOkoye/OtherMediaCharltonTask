package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class LiveData(
    @SerializedName("away_score")
    val awayScore: Int,
    @SerializedName("home_score")
    val homeScore: Int,
    @SerializedName("isLive")
    val isLive: Boolean,
    @SerializedName("match_length_min")
    val matchLengthMin: Int,
    @SerializedName("matchStatus")
    val matchStatus: String,
    @SerializedName("match_winner")
    val matchWinner: String,
    @SerializedName("scoreEntries")
    val scoreEntries: ScoreEntries
)