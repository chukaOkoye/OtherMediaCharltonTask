package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class MatchReport(
    @SerializedName("id")
    val id: Any,
    @SerializedName("path")
    val path: Any
)