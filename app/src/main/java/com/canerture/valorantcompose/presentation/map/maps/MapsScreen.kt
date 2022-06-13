package com.canerture.valorantcompose.presentation.map.maps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.canerture.valorantcompose.common.components.ErrorText

@Composable
fun MapsScreen(
    viewModel: MapsViewModel = hiltViewModel(),
    navigateToMapDetail: (String) -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.getMaps()
    }

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(state.maps) { mapItem ->
                MapItem(
                    map = mapItem,
                    onItemClick = {
                        navigateToMapDetail(it)
                    }
                )
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