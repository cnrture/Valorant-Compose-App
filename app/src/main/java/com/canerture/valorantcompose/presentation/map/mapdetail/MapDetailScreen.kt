package com.canerture.valorantcompose.presentation.map.mapdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.canerture.valorantcompose.R
import com.canerture.valorantcompose.common.components.ErrorText
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MapDetailScreen(
    viewModel: MapDetailViewModel = hiltViewModel()
) {

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
                text = it.displayName.orEmpty(),
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = it.coordinates.orEmpty(),
                style = MaterialTheme.typography.h4
            )
        }

        if (state.error.isNotBlank()) ErrorText(
            state.error,
            Modifier.align(Alignment.CenterHorizontally)
        )

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.White
            )
        }
    }
}