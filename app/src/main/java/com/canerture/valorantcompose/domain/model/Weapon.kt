package com.canerture.valorantcompose.domain.model

import com.canerture.valorantcompose.data.model.weapons.Skin
import com.canerture.valorantcompose.data.model.weapons.WeaponStats

data class Weapon(
    val category: String,
    val displayIcon: String?,
    val displayName: String,
    var skins: List<Skin>,
    val uuid: String,
    val weaponStats: WeaponStats?
)
