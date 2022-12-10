package com.canerture.valorantcompose.domain.model

import com.canerture.valorantcompose.data.model.agents.Ability
import com.canerture.valorantcompose.data.model.agents.Role

data class Agent(
    val abilities: List<Ability>,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val fullPortrait: String,
    val role: Role?,
    val uuid: String
)