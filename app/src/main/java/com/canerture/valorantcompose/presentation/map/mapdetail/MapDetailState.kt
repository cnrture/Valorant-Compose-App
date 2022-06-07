package com.canerture.valorantcompose.presentation.map.mapdetail

import com.canerture.valorantcompose.domain.model.Map

data class MapDetailState constructor(
    val isLoading: Boolean = false,
    val map: Map? = null,
    val error: String = ""
)