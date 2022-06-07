package com.canerture.valorantcompose.presentation.weapon.weapons

import com.canerture.valorantcompose.domain.model.Weapon

data class WeaponsState(
    val isLoading: Boolean = false,
    val weapons: List<Weapon> = emptyList(),
    val error: String = ""
)