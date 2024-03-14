package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class ScoreEntries(
    @SerializedName("ft")
    val ft: Ft,
    @SerializedName("ht")
    val ht: Ht,
    @SerializedName("total")
    val total: Total
)