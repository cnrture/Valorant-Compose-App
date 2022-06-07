package com.canerture.valorantcompose.data.model.competitivetiers

import com.google.gson.annotations.SerializedName

data class CompetitiveTiersResponse(
    @SerializedName("data")
    val data: List<CompetitiveTier>,
    @SerializedName("status")
    val status: Int
)