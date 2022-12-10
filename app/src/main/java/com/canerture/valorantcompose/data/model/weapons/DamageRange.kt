package com.canerture.valorantcompose.data.model.weapons

import com.google.gson.annotations.SerializedName

data class DamageRange(
    @SerializedName("bodyDamage")
    val bodyDamage: Int?,
    @SerializedName("headDamage")
    val headDamage: Double?,
    @SerializedName("legDamage")
    val legDamage: Double?,
    @SerializedName("rangeEndMeters")
    val rangeEndMeters: Int?,
    @SerializedName("rangeStartMeters")
    val rangeStartMeters: Int?
)