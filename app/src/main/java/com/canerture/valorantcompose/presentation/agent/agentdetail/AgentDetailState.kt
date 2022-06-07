package com.canerture.valorantcompose.presentation.agent.agentdetail

import com.canerture.valorantcompose.domain.model.Agent

data class AgentDetailState constructor(
    val isLoading: Boolean = false,
    val agent: Agent? = null,
    val error: String = ""
)