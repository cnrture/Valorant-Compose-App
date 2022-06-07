package com.canerture.valorantcompose.domain.repository

import com.canerture.valorantcompose.data.model.agents.AgentDetailResponse
import com.canerture.valorantcompose.data.model.agents.AgentResponse
import com.canerture.valorantcompose.data.model.competitivetiers.CompetitiveTiersResponse
import com.canerture.valorantcompose.data.model.maps.MapDetailResponse
import com.canerture.valorantcompose.data.model.maps.MapsResponse
import com.canerture.valorantcompose.data.model.weapons.WeaponDetailResponse
import com.canerture.valorantcompose.data.model.weapons.WeaponsResponse

interface ValorantRepository {

    suspend fun getAgents(): AgentResponse

    suspend fun getAgentByUuid(agentUuid: String): AgentDetailResponse

    suspend fun getMaps(): MapsResponse

    suspend fun getMapByUuid(mapUuid: String): MapDetailResponse

    suspend fun getWeapons(): WeaponsResponse

    suspend fun getWeaponByUuid(weaponUuid: String): WeaponDetailResponse

    suspend fun getCompetitiveTiers(): CompetitiveTiersResponse
}