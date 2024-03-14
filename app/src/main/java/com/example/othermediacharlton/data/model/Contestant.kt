package com.example.othermediacharlton.data.model


import com.google.gson.annotations.SerializedName

data class Contestant(
    @SerializedName("code")
    val code: String,
    @SerializedName("crest")
    val crest: Crest,
    @SerializedName("externalId")
    val externalId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("legacyId")
    val legacyId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("officialName")
    val officialName: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("primarySponsor")
    val primarySponsor: Any,
    @SerializedName("secondarySponsors")
    val secondarySponsors: Any,
    @SerializedName("shortName")
    val shortName: String,
    @SerializedName("taxonomy_term")
    val taxonomyTerm: Any
)