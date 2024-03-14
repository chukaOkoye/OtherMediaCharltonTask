package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("liveData")
    val liveData: LiveData,
    @SerializedName("matchInfo")
    val matchInfo: MatchInfo
)