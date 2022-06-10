package com.canerture.valorantcompose.presentation.map.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.canerture.valorantcompose.domain.model.Map
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MapItem(
    map: Map,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onItemClick(map.uuid) }
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.size(width = 200.dp, height = 150.dp)
        ) {
            GlideImage(
                imageModel = map.splash,
                circularReveal = CircularReveal(),
            )

            Text(
                text = map.displayName,
                style = MaterialTheme.typography.h3,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Gray
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