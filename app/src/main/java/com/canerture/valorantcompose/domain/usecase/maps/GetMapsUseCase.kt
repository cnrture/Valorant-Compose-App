package com.canerture.valorantcompose.domain.usecase.maps

import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.data.model.maps.toMap
import com.canerture.valorantcompose.domain.model.Map
import com.canerture.valorantcompose.domain.repository.ValorantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMapsUseCase @Inject constructor(
    private val valorantRepository: ValorantRepository
) {
    operator fun invoke(): Flow<Resource<List<Map>>> = flow {

        try {
            emit(Resource.Loading())
            emit(Resource.Success(valorantRepository.getMaps().data.map { it.toMap() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}