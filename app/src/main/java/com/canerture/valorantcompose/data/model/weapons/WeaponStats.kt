package com.canerture.valorantcompose.data.model.weapons

import com.google.gson.annotations.SerializedName

data class WeaponStats(
    @SerializedName("adsStats")
    val adsStats: AdsStats?,
    @SerializedName("airBurstStats")
    val airBurstStats: Any?,
    @SerializedName("altFireType")
    val altFireType: String?,
    @SerializedName("altShotgunStats")
    val altShotgunStats: Any?,
    @SerializedName("damageRanges")
    val damageRanges: List<DamageRange>?,
    @SerializedName("equipTimeSeconds")
    val equipTimeSeconds: Double?,
    @SerializedName("feature")
    val feature: String?,
    @SerializedName("fireMode")
    val fireMode: Any?,
    @SerializedName("fireRate")
    val fireRate: Double?,
    @SerializedName("firstBulletAccuracy")
    val firstBulletAccuracy: Double?,
    @SerializedName("magazineSize")
    val magazineSize: Int?,
    @SerializedName("reloadTimeSeconds")
    val reloadTimeSeconds: Double?,
    @SerializedName("runSpeedMultiplier")
    val runSpeedMultiplier: Double?,
    @SerializedName("shotgunPelletCount")
    val shotgunPelletCount: Int?,
    @SerializedName("wallPenetration")
    val wallPenetration: String?
)