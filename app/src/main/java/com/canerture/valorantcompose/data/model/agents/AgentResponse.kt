package com.canerture.valorantcompose.data.model.agents

import com.google.gson.annotations.SerializedName

data class AgentResponse(
    @SerializedName("data")
    val data: List<AgentDto>,
    @SerializedName("status")
    val status: Int
)