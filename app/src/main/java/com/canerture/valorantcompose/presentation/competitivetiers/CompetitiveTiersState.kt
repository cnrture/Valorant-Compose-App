package com.canerture.valorantcompose.presentation.competitivetiers

import com.canerture.valorantcompose.domain.model.Tier

data class CompetitiveTiersState(
    val isLoading: Boolean = false,
    val tier: List<Tier> = emptyList(),
    val error: String = ""
)