package com.canerture.valorantcompose.domain.usecase.agents

import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.data.model.agents.toAgent
import com.canerture.valorantcompose.domain.model.Agent
import com.canerture.valorantcompose.domain.repository.ValorantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAgentsUseCase @Inject constructor(
    private val valorantRepository: ValorantRepository
) {
    operator fun invoke(): Flow<Resource<List<Agent>>> = flow {

        try {
            emit(Resource.Loading)
            valorantRepository.getAgents().data?.map { it.toAgent() }?.let {
                emit(Resource.Success(it))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}