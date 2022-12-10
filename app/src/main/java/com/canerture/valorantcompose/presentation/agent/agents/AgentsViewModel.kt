package com.canerture.valorantcompose.presentation.agent.agents

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcompose.common.Resource
import com.canerture.valorantcompose.domain.model.Agent
import com.canerture.valorantcompose.domain.usecase.agents.GetAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val getAgentsUseCase: GetAgentsUseCase
) : ViewModel() {

    private var allAgents = listOf<Agent>()
    private val _state = mutableStateOf(AgentsState())
    val state: State<AgentsState> = _state

    private var _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    init {
        getAgents()
    }

    private fun getAgents() {
        getAgentsUseCase().onEach { result ->
            when (result) {
                Resource.Loading -> _state.value = AgentsState(isLoading = true)
                is Resource.Success -> {
                    result.data.let {
                        _state.value = AgentsState(agents = it)
                        allAgents = it
                    }
                }
                is Resource.Error -> _state.value = AgentsState(error = result.errorMessage)
            }
        }.launchIn(viewModelScope)
    }

    fun searchAgent(query: String) {
        _searchQuery.value = query
        _state.value = AgentsState(isLoading = true)

        val foundAgents =
            allAgents.filter {
                it.displayName.contains(query, true) or
                        it.description.contains(query, true)
            }

        if (foundAgents.isEmpty()) {
            _state.value = AgentsState(error = "No agents matching with your search")
        }   else {
            _state.value = AgentsState(agents = foundAgents)
        }
    }

    fun clearSearchQuery() {
        _state.value = AgentsState(agents = allAgents)
        _searchQuery.value = ""
    }
}