package com.canerture.valorantcompose.presentation.map.mapdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.canerture.valorantcompose.R
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MapDetailScreen(
    mapUuid: String,
    viewModel: MapDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getMapDetail(mapUuid)
    }

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        state.map?.let {

            GlideImage(
                imageModel = it.displayIcon,
                modifier = Modifier.size(300.dp),
                circularReveal = CircularReveal(),
                contentDescription = stringResource(R.string.desc_map_image)
            )

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = it.displayName,
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = it.coordinates,
                style = MaterialTheme.typography.h4
            )
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.White
            )
        }
    }
}