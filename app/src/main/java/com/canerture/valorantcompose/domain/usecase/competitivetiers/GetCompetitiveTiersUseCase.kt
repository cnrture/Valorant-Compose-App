package com.canerture.valorantcompose.domain.usecase.competitivetiers

import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.data.model.competitivetiers.TierDto
import com.canerture.valorantcompose.data.model.competitivetiers.toTier
import com.canerture.valorantcompose.domain.model.Tier
import com.canerture.valorantcompose.domain.repository.ValorantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCompetitiveTiersUseCase @Inject constructor(
    private val valorantRepository: ValorantRepository
) {
    operator fun invoke(): Flow<Resource<List<Tier>>> = flow {

        try {
            emit(Resource.Loading)
            valorantRepository.getCompetitiveTiers().data?.last()?.tiers?.let { tiers ->
                val tiersTemp = arrayListOf<TierDto>()
                tiers.forEach { tier ->
                    if (tier.rankTriangleUpIcon != null) {
                        tiersTemp.add(tier)
                    }
                }
                emit(Resource.Success(tiersTemp.map { it.toTier() }))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}