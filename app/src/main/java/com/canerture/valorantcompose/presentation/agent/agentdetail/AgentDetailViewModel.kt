package com.canerture.valorantcompose.presentation.agent.agentdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcompose.common.Constants
import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.domain.usecase.agents.GetAgentDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    private val getAgentDetailUseCase: GetAgentDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AgentDetailState())
    val state: State<AgentDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_AGENT_ID)?.let { agentId ->
            getAgentDetail(agentId)
        }
    }

    private fun getAgentDetail(agentUuid: String) {
        getAgentDetailUseCase(agentUuid).onEach { result ->
            when (result) {
                Resource.Loading -> _state.value = AgentDetailState(isLoading = true)
                is Resource.Success -> _state.value = AgentDetailState(agent = result.data)
                is Resource.Error -> _state.value = AgentDetailState(error = result.errorMessage)
            }
        }.launchIn(viewModelScope)
    }
}