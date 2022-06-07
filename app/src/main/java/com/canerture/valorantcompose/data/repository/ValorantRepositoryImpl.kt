package com.canerture.valorantcompose.data.repository

import com.canerture.valorantcompose.data.model.agents.AgentDetailResponse
import com.canerture.valorantcompose.data.model.agents.AgentResponse
import com.canerture.valorantcompose.data.model.competitivetiers.CompetitiveTiersResponse
import com.canerture.valorantcompose.data.model.maps.MapDetailResponse
import com.canerture.valorantcompose.data.model.maps.MapsResponse
import com.canerture.valorantcompose.data.model.weapons.WeaponDetailResponse
import com.canerture.valorantcompose.data.model.weapons.WeaponsResponse
import com.canerture.valorantcompose.data.remote.ValorantService
import com.canerture.valorantcompose.domain.repository.ValorantRepository
import javax.inject.Inject

class ValorantRepositoryImpl @Inject constructor(
    private val valorantService: ValorantService
) : ValorantRepository {

    override suspend fun getAgents(): AgentResponse =
        valorantService.getAgents()

    override suspend fun getAgentByUuid(agentUuid: String): AgentDetailResponse =
        valorantService.getAgentByUuid(agentUuid)

    override suspend fun getMaps(): MapsResponse =
        valorantService.getMaps()

    override suspend fun getMapByUuid(mapUuid: String): MapDetailResponse =
        valorantService.getMapByUuid(mapUuid)

    override suspend fun getWeapons(): WeaponsResponse =
        valorantService.getWeapons()

    override suspend fun getWeaponByUuid(weaponUuid: String): WeaponDetailResponse =
        valorantService.getWeaponByUuid(weaponUuid)

    override suspend fun getCompetitiveTiers(): CompetitiveTiersResponse =
        valorantService.getCompetitiveTiers()
}