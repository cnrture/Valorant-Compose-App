package com.canerture.valorantcompose.presentation.map.mapdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcompose.common.Constants
import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.domain.usecase.maps.GetMapDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MapDetailViewModel @Inject constructor(
    private val getMapDetailUseCase: GetMapDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MapDetailState())
    val state: State<MapDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_MAP_ID)?.let { mapId ->
            getMapDetail(mapId)
        }
    }

    private fun getMapDetail(mapUuid: String) {
        getMapDetailUseCase(mapUuid).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = MapDetailState(map = it)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        MapDetailState(
                            error = result.errorMessage ?: "Unexpected error!"
                        )
                }
                is Resource.Loading -> {
                    _state.value = MapDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}