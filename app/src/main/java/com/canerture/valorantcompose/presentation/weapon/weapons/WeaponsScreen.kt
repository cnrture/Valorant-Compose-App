package com.canerture.valorantcompose.presentation.weapon.weapons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
fun WeaponsScreen(
    viewModel: WeaponsViewModel = hiltViewModel(),
    navigateToWeaponDetail: (String) -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.getWeapons()
    }

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(state.weapons) { weaponItem ->
                WeaponItem(
                    weapon = weaponItem,
                    onItemClick = {
                        navigateToWeaponDetail(it)
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