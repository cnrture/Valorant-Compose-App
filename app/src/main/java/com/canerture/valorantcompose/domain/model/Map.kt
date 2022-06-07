package com.canerture.valorantcompose.domain.model

data class Map(
    val coordinates: String,
    val displayIcon: String?,
    val displayName: String,
    val splash: String,
    val uuid: String,
)