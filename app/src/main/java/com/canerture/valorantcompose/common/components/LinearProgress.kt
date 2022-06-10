package com.canerture.valorantcompose.common.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.canerture.valorantcompose.presentation.theme.ValoRed

@Composable
fun LinearProgress(header: String, progress: Float, progressString: String) {
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$header - $progressString",
            style = MaterialTheme.typography.h5,
            color = ValoRed,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(4.dp))
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier.height(10.dp),
            color = ValoRed,
            backgroundColor = Color.White
        )
    }
}