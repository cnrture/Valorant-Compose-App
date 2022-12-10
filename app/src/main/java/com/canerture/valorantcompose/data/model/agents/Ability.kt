package com.canerture.valorantcompose.data.model.agents

import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("description")
    val description: String?,
    @SerializedName("displayIcon")
    val displayIcon: String?,
    @SerializedName("displayName")
    val displayName: String?,
    @SerializedName("slot")
    val slot: String?
)