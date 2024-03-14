package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class MatchInfo(
    @SerializedName("contestant")
    val contestant: List<Contestant>,
    @SerializedName("date")
    val date: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("externalId")
    val externalId: String,
    @SerializedName("externalLastUpdated")
    val externalLastUpdated: Int,
    @SerializedName("externalTicketEventId")
    val externalTicketEventId: Any,
    @SerializedName("firstCallToAction")
    val firstCallToAction: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastUpdated")
    val lastUpdated: Int,
    @SerializedName("legacyId")
    val legacyId: String,
    @SerializedName("matchPreview")
    val matchPreview: MatchPreview,
    @SerializedName("matchReport")
    val matchReport: MatchReport,
    @SerializedName("relatedTags")
    val relatedTags: List<Any>,
    @SerializedName("secondCallToAction")
    val secondCallToAction: Any,
    @SerializedName("tbc")
    val tbc: Boolean,
    @SerializedName("venue")
    val venue: Venue
)