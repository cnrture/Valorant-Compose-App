package com.canerture.valorantcompose.data.model.maps

import com.canerture.valorantcompose.domain.model.Map
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue

data class MapDto(
    @SerializedName("assetPath")
    val assetPath: String?,
    @SerializedName("callouts")
    val callouts: @RawValue List<Callout>?,
    @SerializedName("coordinates")
    val coordinates: String?,
    @SerializedName("displayIcon")
    val displayIcon: String?,
    @SerializedName("displayName")
    val displayName: String?,
    @SerializedName("listViewIcon")
    val listViewIcon: String?,
    @SerializedName("mapUrl")
    val mapUrl: String?,
    @SerializedName("splash")
    val splash: String?,
    @SerializedName("uuid")
    val uuid: String?,
    @SerializedName("xMultiplier")
    val xMultiplier: Double?,
    @SerializedName("xScalarToAdd")
    val xScalarToAdd: Double?,
    @SerializedName("yMultiplier")
    val yMultiplier: Double?,
    @SerializedName("yScalarToAdd")
    val yScalarToAdd: Double?
)

fun MapDto.toMap() = Map(
    coordinates = coordinates,
    displayIcon = displayIcon,
    displayName = displayName,
    splash = splash,
    uuid = uuid
)