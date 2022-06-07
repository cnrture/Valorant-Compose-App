package com.canerture.valorantcompose.presentation.weapon.weapons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.canerture.valorantcompose.domain.model.Weapon
import com.canerture.valorantcompose.navigation.Screen
import com.canerture.valorantcompose.presentation.theme.ValoBlue
import com.canerture.valorantcompose.presentation.theme.ValoRed
import com.canerture.valorantcompose.presentation.theme.ValoYellow
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun WeaponsScreen(
    navController: NavController,
    viewModel: WeaponsViewModel = hiltViewModel()
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
                        navController.navigate("${Screen.WeaponDetail.route}/$it")
                    }
                )
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }
    }
}

@Composable
fun WeaponItem(
    weapon: Weapon,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onItemClick(weapon.uuid) }
            .padding(12.dp),
        backgroundColor = ValoRed
    ) {
        Box(contentAlignment = Alignment.Center) {
            GlideImage(
                imageModel = weapon.displayIcon,
                contentScale = ContentScale.Fit,
                circularReveal = CircularReveal(),
                modifier = Modifier
                    .size(150.dp)
            )

            Text(
                text = weapon.displayName,
                style = MaterialTheme.typography.h5,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                ValoBlue
                            )
                        )
                    )
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}