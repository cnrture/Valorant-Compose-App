package com.canerture.valorantcompose.presentation.weapon.weapondetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.domain.usecase.weapons.GetWeaponDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeaponDetailViewModel @Inject constructor(
    private val getWeaponDetailUseCase: GetWeaponDetailUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WeaponDetailState())
    val state: State<WeaponDetailState> = _state

    fun getWeaponDetail(weaponUuid: String) {
        getWeaponDetailUseCase.getWeaponDetailByUuid(weaponUuid).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = WeaponDetailState(weapon = it)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        WeaponDetailState(
                            error = result.errorMessage ?: "Unexpected error!"
                        )
                }
                is Resource.Loading -> {
                    _state.value = WeaponDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}