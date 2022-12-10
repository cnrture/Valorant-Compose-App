package com.canerture.valorantcompose.data.model.agents

import com.canerture.valorantcompose.domain.model.Agent
import com.google.gson.annotations.SerializedName

data class AgentDto(
    @SerializedName("abilities")
    val abilities: List<Ability>?,
    @SerializedName("assetPath")
    val assetPath: String?,
    @SerializedName("background")
    val background: Any?,
    @SerializedName("backgroundGradientColors")
    val backgroundGradientColors: Any?,
    @SerializedName("bustPortrait")
    val bustPortrait: String?,
    @SerializedName("characterTags")
    val characterTags: Any?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("developerName")
    val developerName: String?,
    @SerializedName("displayIcon")
    val displayIcon: String?,
    @SerializedName("displayIconSmall")
    val displayIconSmall: String?,
    @SerializedName("displayName")
    val displayName: String?,
    @SerializedName("fullPortrait")
    val fullPortrait: String?,
    @SerializedName("fullPortraitV2")
    val fullPortraitV2: String?,
    @SerializedName("isAvailableForTest")
    val isAvailableForTest: Boolean?,
    @SerializedName("isBaseContent")
    val isBaseContent: Boolean?,
    @SerializedName("isFullPortraitRightFacing")
    val isFullPortraitRightFacing: Boolean?,
    @SerializedName("isPlayableCharacter")
    val isPlayableCharacter: Boolean?,
    @SerializedName("killfeedPortrait")
    val killfeedPortrait: String?,
    @SerializedName("role")
    val role: Role?,
    @SerializedName("uuid")
    val uuid: String?,
    @SerializedName("voiceLine")
    val voiceLine: VoiceLine?
)

fun AgentDto.toAgent() = Agent(
    abilities = abilities.orEmpty(),
    description = description.orEmpty(),
    displayIcon = displayIcon.orEmpty(),
    displayName = displayName.orEmpty(),
    fullPortrait = fullPortraitV2 ?: fullPortrait.orEmpty(),
    role = role,
    uuid = uuid.orEmpty()
)