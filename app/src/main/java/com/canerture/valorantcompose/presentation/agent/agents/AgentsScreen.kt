package com.canerture.valorantcompose.presentation.agent.agents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.canerture.valorantcompose.R
import com.canerture.valorantcompose.common.components.ErrorText
import com.canerture.valorantcompose.common.components.SearchBar

@Composable
fun AgentsScreen(
    viewModel: AgentsViewModel = hiltViewModel(),
    navigateToAgentDetail: (String) -> Unit,
) {

    val state = viewModel.state.value
    val searchQuery = viewModel.searchQuery.value

    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(
                searchText = searchQuery,
                placeholderText = stringResource(R.string.search_agent),
                onSearchTextChanged = {
                    viewModel.searchAgent(it)
                },
                onClearClick = {
                    viewModel.clearSearchQuery()
                })

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(state.agents) { agentItem ->
                    AgentItem(
                        agent = agentItem,
                        onItemClick = {
                            navigateToAgentDetail.invoke(it)
                        }
                    )
                }
            }
        }

        if (state.error.isNotBlank()) ErrorText(state.error, Modifier.align(Alignment.Center))

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }
    }
}