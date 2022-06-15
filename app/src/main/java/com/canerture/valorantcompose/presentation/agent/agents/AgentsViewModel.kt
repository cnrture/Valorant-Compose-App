package com.canerture.valorantcompose.presentation.agent.agents

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.domain.usecase.agents.GetAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val getAgentsUseCase: GetAgentsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AgentsState())
    val state: State<AgentsState> = _state

    init {
        getAgents()
    }

    private fun getAgents() {
        getAgentsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = AgentsState(agents = it)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        AgentsState(error = result.errorMessage ?: "Unexpected error!")
                }
                is Resource.Loading -> {
                    _state.value = AgentsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}