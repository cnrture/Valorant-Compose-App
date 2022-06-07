package com.canerture.valorantcompose.presentation.agent.agents

import com.canerture.valorantcompose.domain.model.Agent

data class AgentsState(
    val isLoading: Boolean = false,
    val agents: List<Agent> = emptyList(),
    val error: String = ""
)