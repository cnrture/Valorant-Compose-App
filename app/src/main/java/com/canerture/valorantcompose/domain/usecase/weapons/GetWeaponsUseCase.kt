package com.canerture.valorantcompose.domain.usecase.weapons

import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.data.model.weapons.toWeapon
import com.canerture.valorantcompose.domain.model.Weapon
import com.canerture.valorantcompose.domain.repository.ValorantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeaponsUseCase @Inject constructor(
    private val valorantRepository: ValorantRepository
) {
    operator fun invoke(): Flow<Resource<List<Weapon>>> = flow {

        try {
            emit(Resource.Loading())
            emit(Resource.Success(valorantRepository.getWeapons().data.map { it.toWeapon() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage.orEmpty()))
        }
    }
}