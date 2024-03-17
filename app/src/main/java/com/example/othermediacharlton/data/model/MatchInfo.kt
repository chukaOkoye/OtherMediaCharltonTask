package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class MatchInfo(
    @SerializedName("contestant")
    val contestant: List<Contestant>,
    @SerializedName("date")
    val date: Int,
    val externalTicketEventId: Any,
    @SerializedName("firstCallToAction")
    val firstCallToAction: Any,
    @SerializedName("secondCallToAction")
    val secondCallToAction: Any,
    @SerializedName("venue")
    val venue: Venue
)