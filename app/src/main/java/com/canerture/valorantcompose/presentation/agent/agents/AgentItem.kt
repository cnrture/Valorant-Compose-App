package com.canerture.valorantcompose.presentation.agent.agents

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.canerture.valorantcompose.domain.model.Agent
import com.canerture.valorantcompose.presentation.theme.ValoLightBlue
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AgentItem(
    agent: Agent,
    onItemClick: (String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val imageHeight = (configuration.screenHeightDp / 4f).dp

    Card(
        modifier = Modifier
            .clickable { onItemClick(agent.uuid) }
            .padding(12.dp),
        backgroundColor = ValoLightBlue
    ) {
        Box {
            GlideImage(
                imageModel = agent.displayIcon,
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(),
                modifier = Modifier.size(imageHeight)
            )

            Text(
                text = agent.displayName,
                style = MaterialTheme.typography.h5,
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