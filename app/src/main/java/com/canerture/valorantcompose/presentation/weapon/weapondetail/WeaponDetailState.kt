package com.canerture.valorantcompose.presentation.weapon.weapondetail

import com.canerture.valorantcompose.domain.model.Weapon

data class WeaponDetailState constructor(
    val isLoading: Boolean = false,
    val weapon: Weapon? = null,
    val error: String = ""
)