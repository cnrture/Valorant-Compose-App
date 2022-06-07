package com.canerture.valorantcompose.presentation.map.maps

import com.canerture.valorantcompose.domain.model.Map

data class MapsState(
    val isLoading: Boolean = false,
    val maps: List<Map> = emptyList(),
    val error: String = ""
)