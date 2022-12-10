package com.canerture.valorantcompose.presentation.weapon.weapondetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcompose.common.Constants
import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.domain.usecase.weapons.GetWeaponDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeaponDetailViewModel @Inject constructor(
    private val getWeaponDetailUseCase: GetWeaponDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(WeaponDetailState())
    val state: State<WeaponDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_WEAPON_ID)?.let { weaponId ->
            getWeaponDetail(weaponId)
        }
    }

    private fun getWeaponDetail(weaponUuid: String) {
        getWeaponDetailUseCase.getWeaponDetailByUuid(weaponUuid).onEach { result ->
            when (result) {
                Resource.Loading -> _state.value = WeaponDetailState(isLoading = true)
                is Resource.Success -> _state.value = WeaponDetailState(weapon = result.data)
                is Resource.Error -> _state.value = WeaponDetailState(error = result.errorMessage)
            }
        }.launchIn(viewModelScope)
    }
}